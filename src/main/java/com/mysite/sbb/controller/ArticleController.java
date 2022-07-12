package com.mysite.sbb.controller;

import com.mysite.sbb.dao.ArticleRepository;
import com.mysite.sbb.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usr/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @RequestMapping("/test")
    @ResponseBody
    public String testFunc() {
        return "test";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Article> showList() {
        return articleRepository.findAll();
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Article showArticleDetail(@RequestParam long id, String name) {
        Optional<Article> article = articleRepository.findById(id);
        return article.orElse(null);

    }
    @RequestMapping("/doModify")
    @ResponseBody
    public Article doModify(long id, String title, String body) {
        Article article = articleRepository.findById(id).get();// 조건에 맞는 데이터 가져오기
        if( title != null ) {
            article.setTitle(title); //불러온 데이터 수정
        }

        if( body != null ) {
            article.setBody(body); //불러온 데이터 수정
        }

        articleRepository.save(article); //수정된 데이터 db에 저장

        return article;
    }

    @RequestMapping("/doDelete")
    @ResponseBody
    public String doDelete(long id) {
        if (articleRepository.existsById(id)==false){
            return "%d번 게시물이 없습니다".formatted(id);
        }
        articleRepository.deleteById(id); // 삭제
        return "%d번 게시물이 삭제되었습니다".formatted(id);
    }
    @RequestMapping("/findByTitle")
    @ResponseBody
    public List<Article> findByTitle(@RequestParam String title) {
        List<Article> articles = articleRepository.findByTitle(title);
        return articles;
    }
}
