package aiss.videominer.repository;

import aiss.videominer.model.Caption;
import aiss.videominer.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaptionRepository extends JpaRepository<Caption, String> {
}
