package mat.study.store.product.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@WebFilter(filterName = "RequestCachingFilter", urlPatterns = "/api/v1/*")
public class LoggingFilter extends OncePerRequestFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);
  private static final List<String> MASKED_FIELDS = Arrays.asList("password", "token");

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
    ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

    long startTime = System.currentTimeMillis();
    filterChain.doFilter(requestWrapper, responseWrapper);
    long timeTaken = System.currentTimeMillis() - startTime;

    String requestBody = getStringValue(requestWrapper.getContentAsByteArray(),
        request.getCharacterEncoding());
    String responseBody = getStringValue(responseWrapper.getContentAsByteArray(),
        response.getCharacterEncoding());

    logRequest(request, requestBody);
    logResponse(response, responseBody, timeTaken);
    responseWrapper.copyBodyToResponse();
  }

  private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
    try {
      return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return "";
  }

  private void logRequest(HttpServletRequest request, String requestBody) {
    LOGGER.info(
        "REQUEST: METHOD={}; URI={}; PAYLOAD={}",
        request.getMethod(), request.getRequestURI(), maskSensitiveInfo(requestBody));
  }

  private void logResponse(HttpServletResponse response, String responseBody, long timeTaken) {
    LOGGER.info(
        "RESPONSE: CODE={}; BODY={}; TIM TAKEN={}",
        response.getStatus(), maskSensitiveInfo(responseBody),
        timeTaken);
  }

  private String maskSensitiveInfo(String payload) {

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

}
