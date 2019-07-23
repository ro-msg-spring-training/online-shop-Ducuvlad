package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.msg.learning.shop.model.Location;

import java.util.Collection;

/*

 */
public interface LocationRepository extends BaseRepository<Location, Integer> {
    @Query("SELECT l FROM Location l where l.id = id")
    Collection<Location> findOrderDetailsByOrderID(@Param("id") int id);

}
