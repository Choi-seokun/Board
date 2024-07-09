package JCBoard.Dto;

import java.time.LocalDateTime;

public class CommentDto {
    private Long id;
    private String commentContent; //댓글 내용
    private String nickName; //닉네임
    private String password; //비밀번호

    private Long boardid; //게시글 번호

    public Long getBoardid() {
        return boardid;
    }

    public void setBoardid(Long boardid) {
        this.boardid = boardid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    private LocalDateTime time; //날짜
}
