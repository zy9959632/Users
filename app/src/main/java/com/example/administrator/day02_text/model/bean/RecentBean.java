package com.example.administrator.day02_text.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class RecentBean {
        /**
         * news_id : 9717396
         * url : http://news-at.zhihu.com/api/2/news/9717396
         * thumbnail : https://pic1.zhimg.com/v2-ec1ab0e758baeb18884795c8a4825ca4.jpg
         * title : 瞎扯 · 如何正确地吐槽
         */
        @Id
        private Long news_id;
        private String url;
        private String thumbnail;
        private String title;
        @Generated(hash = 331941798)
        public RecentBean(Long news_id, String url, String thumbnail, String title) {
            this.news_id = news_id;
            this.url = url;
            this.thumbnail = thumbnail;
            this.title = title;
        }
        @Generated(hash = 1697461393)
        public RecentBean() {
        }
        public Long getNews_id() {
            return this.news_id;
        }
        public void setNews_id(Long news_id) {
            this.news_id = news_id;
        }
        public String getUrl() {
            return this.url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        public String getThumbnail() {
            return this.thumbnail;
        }
        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
        public String getTitle() {
            return this.title;
        }
        public void setTitle(String title) {
            this.title = title;
        }

    }