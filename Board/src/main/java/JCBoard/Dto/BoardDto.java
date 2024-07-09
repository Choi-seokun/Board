package JCBoard.Dto;

import lombok.Getter;

import java.time.LocalDateTime;


public class BoardDto {

    private Long id;
    private String title; //제목
    private String nickName; //닉네임
    private LocalDateTime time; //날짜

    public BoardDto(){}
    public BoardDto(Long id, String title, String nickName, LocalDateTime time) {
        this.id = id;
        this.title = title;
        this.nickName = nickName;
        this.time = time;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    private String content; //내용
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
