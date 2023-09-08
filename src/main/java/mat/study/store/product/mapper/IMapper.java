package mat.study.store.product.mapper;

public interface IMapper<I, O> {
  O map(I in);
  void update(O on,I in);
}
