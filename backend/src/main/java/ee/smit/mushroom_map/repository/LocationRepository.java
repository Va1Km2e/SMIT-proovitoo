package ee.smit.mushroom_map.repository;

import ee.smit.mushroom_map.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long>,
        JpaSpecificationExecutor<LocationEntity> {
}
