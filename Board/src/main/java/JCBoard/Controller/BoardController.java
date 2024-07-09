package JCBoard.Controller;

import JCBoard.Dto.BoardDto;
import JCBoard.Dto.CommentDto;
import JCBoard.Entity.BoardEntity;
import JCBoard.Entity.CommentEntity;
import JCBoard.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Board")
public class BoardController {

    @Autowired
    private BoardService boardService;
    // @PageableDefault(page = 1) : page는 기본으로 1페이지를 보여준다.
    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardDto> postsPages = boardService.paging(pageable);

        /*
         * blockLimit : page 개수 설정
         * 현재 사용자가 선택한 페이지 앞 뒤로 3페이지씩만 보여준다.
         * ex : 현재 사용자가 4페이지라면 2, 3, (4), 5, 6
         */
        int blockLimit = 3;
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), postsPages.getTotalPages());


        model.addAttribute("postsPages", postsPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "home";
    }
    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model, @PageableDefault(page = 1) Pageable pageable) {
        Page<BoardDto> postsPages = boardService.search(query, pageable);

        int blockLimit = 3;
        int startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = Math.min((startPage + blockLimit - 1), postsPages.getTotalPages());

        model.addAttribute("postsPages", postsPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/{id}")
    public String getBoard(@PathVariable Long id, Model model)
    {
        BoardDto boardDto= boardService.getBoard(id);
        List<CommentDto> commentDto=boardService.getComments(id);
        model.addAttribute("id", id);
        model.addAttribute("board", boardDto);
        model.addAttribute("comment",commentDto);
        return "read";
    }
    @GetMapping("/write")
    public String write() {
        return "write";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("boardid", id);
        return "update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "delete";
    }

    @PostMapping("/writeinfo")
    @ResponseBody
    public void writeinfo(@RequestBody BoardEntity writeinfo){
        boardService.Writing(writeinfo);
        return;
    }

    @PutMapping("/updateinfo")
    @ResponseBody
    public boolean updateinfo(@RequestBody BoardEntity updateinfo){
        return boardService.Update(updateinfo);
    }

    @PostMapping("/comment")
    @ResponseBody
    public void comment(@RequestBody CommentEntity comment){
        System.out.println("dz");
        boardService.Writingcomment(comment);
        return;
    }

    @DeleteMapping("/deletedata")
    @ResponseBody
    public boolean deletedata(@RequestParam Long id, @RequestParam String password){
        return boardService.Delete(id, password);
    }
}
