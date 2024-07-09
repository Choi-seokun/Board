package JCBoard.Service;

import JCBoard.Dto.BoardDto;
import JCBoard.Dto.CommentDto;
import JCBoard.Entity.BoardEntity;
import JCBoard.Entity.CommentEntity;
import JCBoard.Repository.BoardRepository;
import JCBoard.Repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private CommentRepository commentRepository;

    public Page<BoardDto> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 10; //한 페이지 당 글의 개수 지정

        // 한 페이지당 10개식 글을 보여주고 정렬 기준은 ID기준으로 내림차순
        Page<BoardEntity> postsPages = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        // 목록 : id, title, content, author
        Page<BoardDto> BoardDtos = postsPages.map(
                post -> new BoardDto(post.getId(), post.getTitle(), post.getNickName(), post.getTime()));

        return BoardDtos;
    }
    public Page<BoardDto> search(String query, Pageable pageable) {
        int page = pageable.getPageNumber() - 1; // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 10; //한 페이지 당 글의 개수 지정

        // 한 페이지당 10개씩 글을 보여주고 정렬 기준은 ID 기준으로 내림차순
        Page<BoardEntity> postsPages = boardRepository.findByTitleContaining(query,PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        // 목록: id, title, nickName, time
        Page<BoardDto> boardDtos = postsPages.map(
                post -> new BoardDto(post.getId(), post.getTitle(), post.getNickName(), post.getTime()));

        return boardDtos;
    }

    public BoardDto getBoard(Long id) {
        Optional<BoardEntity> entity = boardRepository.findById(id);
        return entity.map(this::convertEntityToDto2).orElseThrow(() -> new RuntimeException("Board not found"));
    }

    public List<CommentDto> getComments(Long boardid) {
        List<CommentEntity> entities = commentRepository.findByBoardid(boardid);
        return entities.stream().map(this::convertEntityToDto3).collect(Collectors.toList());
    }

    private CommentDto convertEntityToDto3(CommentEntity entity) { //해당 게시글 댓글
        CommentDto dto = new CommentDto();
        dto.setId(entity.getCommentid());
        dto.setBoardid(entity.getBoardid());
        dto.setTime(entity.getTime());
        dto.setPassword(entity.getPassword());
        dto.setCommentContent(entity.getContent());
        dto.setNickName(entity.getNickname());
        return dto;
    }

    public void Writing(BoardEntity boardEntity){
        boardRepository.save(boardEntity);
    }

    public void Writingcomment(CommentEntity commentEntity){
        commentRepository.save(commentEntity);
    }

    public boolean Update(BoardEntity newinfo){
        Optional<BoardEntity> oldinfo = boardRepository.findById(newinfo.getId());
        if(!oldinfo.get().getPassword().equals(newinfo.getPassword())){
            return false;
        }
        oldinfo.get().setTitle(newinfo.getTitle());
        oldinfo.get().setContent(newinfo.getContent());
        boardRepository.save(oldinfo.get());
        return true;
    }

    public boolean Delete(Long id, String password){
        BoardEntity board = boardRepository.findById(id).get();
        if (board.getPassword().equals(password)) {
            // 게시글에 연결된 댓글 삭제
            commentRepository.deleteByBoardid(id);
            // 게시글 삭제
            boardRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private BoardDto convertEntityToDto2(BoardEntity entity) { //해당 게시글
        BoardDto dto = new BoardDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setNickName(entity.getNickName());
        dto.setTime(entity.getTime());
        return dto;
    }
}

