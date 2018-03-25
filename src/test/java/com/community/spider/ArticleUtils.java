package com.community.spider;

import com.community.dao.ArticleDao;
import com.community.dao.UserDao;
import com.community.entity.Article;
import com.community.entity.Image;
import com.community.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ArticleUtils {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private UserDao userDao;

    private static Map<String, User> userMap = new HashMap<>();

    @Test
    public void insert() {
        String pathName = "E:\\Article";
        String[] files = new File(pathName).list();
        for (int i = 0 ; i < files.length; i++) {
            File file = new File(pathName + "\\" + files[i]);
            try {
                BufferedReader bfr = new BufferedReader(new FileReader(file));
                StringBuffer html = new StringBuffer();
                String line = bfr.readLine();
                while(line != null) {
                    html.append(line + "\n");
                    line = bfr.readLine();
                }
                parse(html.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("文件解析异常:" + files[i]);
                e.printStackTrace();
            }
        }
//        for(String key : userMap.keySet()) {
//            User user = userMap.get(key);
//            userDao.insert(user);
//        }
    }
    private void parse(String html) throws Exception{
        Document document = Jsoup.parse(html);
        Element detailElement = document.getElementsByClass("aw-question-detail").get(0);
        String title = detailElement.getElementsByClass("mod-head").get(0).getElementsByTag("h1").get(0).text();
//        System.out.println(title);
        Element body = detailElement.getElementsByClass("mod-body").get(0);
        String content = body.getElementsByClass("markitup-box").get(0).html();
//        System.out.println(content);
        String date = body.getElementsByClass("clearfix").get(0).getElementsByTag("em").get(0).text();
//        System.out.println(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date createTime ;
        try {
            createTime = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            createTime = new Date();
        }
//        System.out.println(createTime);
        Element userElement = document.getElementsByClass("user-detail").get(0);
        Element userBody = userElement.getElementsByClass("mod-body").get(0);
        String iconUrl = userBody.getElementsByTag("dt").get(0).getElementsByTag("a").attr("href");
//        System.out.println(iconUrl);
        String userName = userBody.getElementsByTag("dd").get(0).getElementsByTag("a").text().replaceAll("OpenSkill", "技术分享社区");
        String sign = userBody.getElementsByTag("dd").get(0).getElementsByTag("p").text().replaceAll("OpenSkill", "技术分享社区");
//        System.out.println(userName);
//        System.out.println(sign);
        User user = new User();
        if (userMap.get(userName) == null) {
            if (StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(sign)) {
                user.setUserName(userName);
                user.setPassword("888888");
                user.setSignature(sign);
                Image image = new Image();
                image.setUrl(iconUrl);
                user.setIcon(image);
                userDao.insert(user);
                userMap.put(userName, user);
            }
        } else {
            user = userMap.get(userName);
        }
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setCreateTime(createTime);
        article.setUser(user);
        articleDao.insert(article);
    }


    public static void getAllHtml() {
        for (int i = 2 ; i <= 602 ;i++) {
            String urlStr = "http://openskill.cn/article/" + i;
            try {
                URL url = new URL(urlStr);
                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                httpConn.setConnectTimeout(15 * 1000);
                httpConn.setReadTimeout(15 * 1000);
                httpConn.setDoInput(true);
                httpConn.setRequestMethod("GET");
                httpConn.setUseCaches(false);
                httpConn.setRequestProperty("User-Agent",
                        "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
                httpConn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
                httpConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
                httpConn.setRequestProperty("Accept-Encoding", "gzip, deflate");
                httpConn.connect();
                InputStream inputStream = httpConn.getInputStream();
                BufferedReader breader = new BufferedReader(new InputStreamReader(inputStream));
                String info = breader.readLine();
                StringBuffer sb = new StringBuffer();
                while (info != null) {
                    info = breader.readLine();
                    sb.append(info + "\n");
                }
                File file = new File("E:\\Article\\article_html_" + i + ".txt");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                BufferedWriter bfw = new BufferedWriter(new FileWriter(file, true));
                bfw.write(sb.toString());
                bfw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
