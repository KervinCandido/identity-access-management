package br.com.fiap.restaurant.iam.core.gateway;

import br.com.fiap.restaurant.iam.core.domain.model.MenuItem;
import br.com.fiap.restaurant.iam.core.domain.pagination.Page;
import br.com.fiap.restaurant.iam.core.domain.pagination.PagedQuery;

import java.util.List;
import java.util.Optional;

public interface MenuItemGateway {
    MenuItem save(MenuItem menuItem, Long restaurantId);
    Optional<MenuItem> findById(Long id);
    List<MenuItem> findByRestaurantId(Long restaurantId);
    void deleteById(Long id);
    boolean existsByNameAndRestaurantId(String name, Long restaurantId);   // Para evitar itens duplicados no cardápio de um restaurante
    Optional<Long> findRestaurantIdByItemId(Long itemId);
    Page<MenuItem> findByRestaurant(PagedQuery<Long> restaurantId);
}
