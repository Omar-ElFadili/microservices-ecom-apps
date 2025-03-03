package org.sid.order.repositories;

import org.sid.order.dtos.OrderLineResponse;
import org.sid.order.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    @Query("")
    List<OrderLine> findAllByOrderId(Integer orderId);
}
