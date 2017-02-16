package com.yjs.ganhuo.bean;

import java.util.List;

/**
 * Created by yangjingsong on 16/6/16.
 */
public class ZhihuDailyEntity {

    /**
     * date : 20160615
     * stories : [{"images":["http://pic3.zhimg.com/03a95f2746023cbc443a4802935c7faa.jpg"],"type":0,"id":8381570,"ga_prefix":"061522","title":"小事 · 为了找男友，我被骗进传销组织"},{"images":["http://pic1.zhimg.com/bc3722f661b435903e2e7ae139e6aa28.jpg"],"type":0,"id":8446943,"ga_prefix":"061521","title":"先不谈买票房丑闻，《叶问 3》到底值不值得看？"},{"images":["http://pic3.zhimg.com/7960f9ec5a0986dd6bf3e4492ac45616.jpg"],"type":0,"id":8444365,"ga_prefix":"061520","title":"拿了你的钱，保险公司也没闲着"},{"images":["http://pic2.zhimg.com/65d1fee60a4ca07a169264aaf90f5dfd.jpg"],"type":0,"id":8446704,"ga_prefix":"061519","title":"所谓的简化字「爱无心」，果真如此吗？"},{"images":["http://pic3.zhimg.com/552a83a2a628f03b04e8fbd741a0c67e.jpg"],"type":0,"id":8446595,"ga_prefix":"061518","title":"和女朋友或男朋友第一次睡在一起是什么感觉？"},{"images":["http://pic3.zhimg.com/c2da0831f278097f36ed8d205988de62.jpg"],"type":0,"id":8440977,"ga_prefix":"061517","title":"「911」 后美国最大的恐怖袭击发生了"},{"images":["http://pic4.zhimg.com/6db2879fb75faa337614bd916d269d5b.jpg"],"type":0,"id":8445937,"ga_prefix":"061516","title":"买来一幅画做装饰，挑个什么样的画框好呢？"},{"title":"Excel 里这个你可能没太用过的功能，其实是办公利器（多图）","ga_prefix":"061515","images":["http://pic4.zhimg.com/72c1df7a83c53d2dcb19f4781de71a17.jpg"],"multipic":true,"type":0,"id":8441117},{"images":["http://pic1.zhimg.com/1415a963d71d021f0617f8d007343010.jpg"],"type":0,"id":8446150,"ga_prefix":"061514","title":"最富有的时候，加上校服也只有三套衣服"},{"images":["http://pic4.zhimg.com/73841f38dbeac1abe10a0155943375fb.jpg"],"type":0,"id":8446213,"ga_prefix":"061513","title":"除了 iOS 大更新，WWDC 还有什么亮点值得关注？"},{"images":["http://pic1.zhimg.com/46e0ce12c6b822f48ca95d4b30606da4.jpg"],"type":0,"id":8445890,"ga_prefix":"061512","title":"大误 · 撞死了一位老人，而他竟是十年前\u2026\u2026"},{"images":["http://pic1.zhimg.com/3496023a5a6426737e53af5de3d07adc.jpg"],"type":0,"id":8441351,"ga_prefix":"061511","title":"考上大学了，我想谢谢几百年前的进士老乡们"},{"images":["http://pic3.zhimg.com/14fac3878d4a7ccf6ef37bd148f041ba.jpg"],"type":0,"id":8434735,"ga_prefix":"061510","title":"神剧《我爱我家》的导演，当年还在这个大学社团画海报"},{"images":["http://pic3.zhimg.com/4eebffc44480e56bc0c70c55a380c6ca.jpg"],"type":0,"id":8441145,"ga_prefix":"061509","title":"牵涉四大政治敏感议题，奥兰多枪击案将如何影响美国大选？"},{"images":["http://pic3.zhimg.com/7a5a066eb079c4522b5cc04afa292e3a.jpg"],"type":0,"id":8443660,"ga_prefix":"061508","title":"他在生命中的最后一个端午，让 5 个人重获新生"},{"images":["http://pic2.zhimg.com/f9f022431b36f9169bdce346d7cad775.jpg"],"type":0,"id":8405269,"ga_prefix":"061507","title":"每天对无数人做出评价，但这些评价真的准确吗？"},{"images":["http://pic4.zhimg.com/a1613e5d8581bfe0d6915bcbaf9f091b.jpg"],"type":0,"id":8443687,"ga_prefix":"061507","title":"书单太长怎么办？答案只有 16 个字"},{"images":["http://pic4.zhimg.com/db537dc9d6b957215a83ea07da4f1d47.jpg"],"type":0,"id":8417848,"ga_prefix":"061507","title":"选个好专业 · 如果我是____你会爱我吗？"},{"images":["http://pic1.zhimg.com/9b203b3848d69c1a0c96cad49f9f8d08.jpg"],"type":0,"id":8444188,"ga_prefix":"061507","title":"读读日报 24 小时热门 TOP 5 · 北京出现了一堆「性别友善」厕所"},{"images":["http://pic4.zhimg.com/0bb13fbd2d13ae6af6d14d364f2d976b.jpg"],"type":0,"id":8438693,"ga_prefix":"061506","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    /**
     * images : ["http://pic3.zhimg.com/03a95f2746023cbc443a4802935c7faa.jpg"]
     * type : 0
     * id : 8381570
     * ga_prefix : 061522
     * title : 小事 · 为了找男友，我被骗进传销组织
     */

    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public static class StoriesBean {
        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
