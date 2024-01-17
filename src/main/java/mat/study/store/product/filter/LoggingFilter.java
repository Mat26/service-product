package mat.study.store.product.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@WebFilter(filterName = "RequestCachingFilter", urlPatterns = "/*")
public class LoggingFilter extends OncePerRequestFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);
  private static final String X_BPI_CORRELATION_ID = "x-bpi-correlation_id";
  private static final List<String> MASKED_FIELDS = Arrays.asList("password", "token");

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (isAsyncDispatch(request)) {
      filterChain.doFilter(request, response);
    } else {
      doFilterWrapped(wrapRequest(request), wrapResponse(response), filterChain);
    }
  }

  protected void doFilterWrapped(ContentCachingRequestWrapper request,
                                 ContentCachingResponseWrapper response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      if (MDC.get(X_BPI_CORRELATION_ID) == null) {
        MDC.put(X_BPI_CORRELATION_ID, UUID.randomUUID().toString());
      }
      request.getInputStream();

      beforeRequest(request);
      filterChain.doFilter(request, response);
    } finally {
      afterRequest(request, response);
      response.copyBodyToResponse();
      MDC.remove(X_BPI_CORRELATION_ID);
    }
  }

  protected void beforeRequest(ContentCachingRequestWrapper request) {
    LOGGER.info("Server has received a request on thread {}", Thread.currentThread());
    logRequestHeader(request);
  }

  protected void afterRequest(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) throws IOException {
    LOGGER.info("Server responded with a response on thread {}", Thread.currentThread());
    byte[] content = request.getContentAsByteArray();
    if (content.length > 0) {
      String payload = new String(request.getContentAsByteArray(), StandardCharsets.UTF_8);
      LOGGER.info("Request payload: [{}]", maskSensitiveInfo(payload));
    }

    logResponse(response);
  }
  private String maskSensitiveInfo(String payload) throws IOException {

    for (String field : MASKED_FIELDS) {
      String regex = "\\b" + field + "\\b\":\"(.*?)\"";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(payload);
      while (matcher.find()) {
        String fieldValue = matcher.group(1);
        payload = payload.replace(fieldValue, "*****");
      }
    }
    return payload;
  }

  private void logRequestHeader(ContentCachingRequestWrapper request) {
    String queryString = request.getQueryString();
    if (queryString == null) {
      LOGGER.info("> {} {}", request.getMethod(), request.getRequestURI());
    } else {
      LOGGER.info("> {} {}?{}", request.getMethod(), request.getRequestURI(), queryString);
    }

    List<String> headerNames = Collections.list(request.getHeaderNames());
    StringBuilder headers = new StringBuilder();

    headerNames.sort(Comparator.naturalOrder());
    headerNames.forEach(headerName -> Collections.list(request.getHeaders(headerName))
        .forEach(headerValue -> headers.append("> ")
            .append(headerName).append(": ")
            .append(headerValue).append("\n")));
    LOGGER.info("{}", headers);
  }

  private void logResponse(ContentCachingResponseWrapper response) throws IOException {
    int status = response.getStatus();
    LOGGER.info(" > {} {}", status, HttpStatus.valueOf(status).getReasonPhrase());

    List<String> headerNames = new ArrayList<>(response.getHeaderNames());
    StringBuilder headers = new StringBuilder();

    headerNames.sort(Comparator.naturalOrder());
    headerNames.forEach(headerName -> response.getHeaders(headerName)
        .forEach(headerValue -> headers.append("> ")
            .append(headerName).append(": ")
            .append(headerValue).append("\n")));
    LOGGER.info("{}", headers);

    byte[] content = response.getContentAsByteArray();
    if (content.length > 0) {
      String payload = new String(response.getContentAsByteArray(), StandardCharsets.UTF_8);
      LOGGER.info("Response payload: [{}]", maskSensitiveInfo(payload));
    }
  }

  private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
    if (request instanceof ContentCachingRequestWrapper) {
      return (ContentCachingRequestWrapper) request;
    } else {
      return new ContentCachingRequestWrapper(request);
    }
  }

  private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
    if (response instanceof ContentCachingResponseWrapper) {
      return (ContentCachingResponseWrapper) response;
    } else {
      return new ContentCachingResponseWrapper(response);
    }
  }
}