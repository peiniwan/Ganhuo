package ganhuo.ly.com.ganhuo.mvp.entity;

import java.util.List;

/**
 * Created by liuyu1 on 2017/8/22.
 */

public class HuaResults {

    private List<PinsBean> pins;

    public List<PinsBean> getPins() {
        return pins;
    }

    public void setPins(List<PinsBean> pins) {
        this.pins = pins;
    }

    public static class PinsBean {
        /**
         * pin_id : 1306268429
         * user_id : 888052
         * board_id : 2443219
         * file_id : 25139841
         * file : {"id":25139841,"farm":"farm1","bucket":"hbimg","key":"5d5a6f0638e75d7e21ae113e3c573421bfe720291a48a-CEXw9Z","type":"image/jpeg","width":481,"height":712,"frames":1,"theme":"030303"}
         * media_type : 0
         * source : pinterest.com
         * link : http://pinterest.com/pin/283726845247814773/
         */

        private int pin_id;
        private int user_id;
        private int board_id;
        private int file_id;
        private FileBean file;
        private int media_type;
        private String source;
        private String link;

        public String getRaw_text() {
            return raw_text;
        }

        public void setRaw_text(String raw_text) {
            this.raw_text = raw_text;
        }

        private String raw_text;

        public int getPin_id() {
            return pin_id;
        }

        public void setPin_id(int pin_id) {
            this.pin_id = pin_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getBoard_id() {
            return board_id;
        }

        public void setBoard_id(int board_id) {
            this.board_id = board_id;
        }

        public int getFile_id() {
            return file_id;
        }

        public void setFile_id(int file_id) {
            this.file_id = file_id;
        }

        public FileBean getFile() {
            return file;
        }

        public void setFile(FileBean file) {
            this.file = file;
        }

        public int getMedia_type() {
            return media_type;
        }

        public void setMedia_type(int media_type) {
            this.media_type = media_type;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public static class FileBean {
            /**
             * id : 25139841
             * farm : farm1
             * bucket : hbimg
             * key : 5d5a6f0638e75d7e21ae113e3c573421bfe720291a48a-CEXw9Z
             * type : image/jpeg
             * width : 481
             * height : 712
             * frames : 1
             * theme : 030303
             */

            private int id;
            private String farm;
            private String bucket;
            private String key;
            private String type;
            private int width;
            private int height;
            private int frames;
            private String theme;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getFarm() {
                return farm;
            }

            public void setFarm(String farm) {
                this.farm = farm;
            }

            public String getBucket() {
                return bucket;
            }

            public void setBucket(String bucket) {
                this.bucket = bucket;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getFrames() {
                return frames;
            }

            public void setFrames(int frames) {
                this.frames = frames;
            }

            public String getTheme() {
                return theme;
            }

            public void setTheme(String theme) {
                this.theme = theme;
            }
        }
    }
}
