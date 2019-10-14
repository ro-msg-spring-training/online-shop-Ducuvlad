package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.model.Revenue;

import java.sql.Date;
import java.util.List;
/*

 */

public interface RevenueRepository extends BaseRepository<Revenue, Integer> {
    List<Revenue> getAllByDate(Date date);
}
