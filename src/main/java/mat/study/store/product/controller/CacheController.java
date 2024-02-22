package mat.study.store.product.controller;

import mat.study.store.product.controller.api.CacheApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class CacheController implements CacheApi {

  private final CacheManager cacheManager;

  @Autowired
  public CacheController(CacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }

  @Override
  public ResponseEntity<Object> getAllCachesEntries() {
    List<Object> caches = new ArrayList<>();
    Collection<String> cacheNames = cacheManager.getCacheNames();
    if (!cacheNames.isEmpty()) {
      cacheNames.forEach(cacheName -> {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
          caches.add(cache.getNativeCache());
        }
      });
      return ResponseEntity.ok(caches);
    } else {
      return ResponseEntity.noContent().build();
    }
  }
}
