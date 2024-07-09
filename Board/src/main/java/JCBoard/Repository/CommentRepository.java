package JCBoard.Repository;

import JCBoard.Entity.BoardEntity;
import JCBoard.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByBoardid(Long boardid);
    void deleteByBoardid(Long boardId);
}
