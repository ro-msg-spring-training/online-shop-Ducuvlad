package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ro.msg.learning.shop.model.BaseEntity;

import javax.transaction.Transactional;
import java.io.Serializable;

/*

 */
@NoRepositoryBean
@Transactional
public interface BaseRepository<T extends BaseEntity<I>, I extends Serializable>
        extends JpaRepository<T, I> {
}