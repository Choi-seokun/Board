package JCBoard.Repository;

import JCBoard.Dto.BoardDto;
import JCBoard.Entity.BoardEntity;
import JCBoard.Entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    Page<BoardEntity> findByTitleContaining(String title, Pageable pageable);
}