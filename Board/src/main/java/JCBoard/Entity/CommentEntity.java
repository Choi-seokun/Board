package JCBoard.Entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class CommentEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentid;
    private String content;
    private String password;
    private String nickname;
    private Long boardid; //글 번호
    @CreatedDate
    private LocalDateTime time;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Long getBoardid() {
        return boardid;
    }
    public void setBoardid(Long boardid) {
        this.boardid = boardid;
    }
    public Long getCommentid() {
        return commentid;
    }
    public void setCommentid(Long commentid) {
        this.commentid = commentid;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
