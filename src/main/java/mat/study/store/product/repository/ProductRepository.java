package mat.study.store.product.repository;

import mat.study.store.product.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
  List<Product> findAllProductsByCategory(@Param("categoryId") Long categoryId);

  @EntityGraph(value = "Product.category")
  Optional<Product> findById(Long id);

  Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);

  Page<Product> findByPriceBetween(@Param("minprice") Double minPrice,@Param("maxprice") Double maxPrice, Pageable pageable);
}
