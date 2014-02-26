/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapObjects;

/**
 *
 * @author Dariusz
 */
public class MapInfo {

    public static class DoorInfo {

        private int locX;
        private int locY;
        private int nextMap;

        public DoorInfo(int locX, int locY, int nextMap) {
            this.locX = locX;
            this.locY = locY;
            this.nextMap = nextMap;
        }

        public int getLocX() {
            return locX;
        }

        public int getLocY() {
            return locY;
        }

        public int getNextMap() {
            return nextMap;
        }
    }

    public static class MonsterInfo {

        private int locX;
        private int locY;
        private int difficulty;
        
        public MonsterInfo(){};

        public MonsterInfo(int locX, int locY, int difficulty) {
            this.locX = locX;
            this.locY = locY;
            this.difficulty = difficulty;
        }

        public int getLocX() {
            return locX;
        }

        public int getLocY() {
            return locY;
        }

        public int getDifficulty() {
            return difficulty;
        }

        public void setLocX(int locX) {
            this.locX = locX;
        }

        public void setLocY(int locY) {
            this.locY = locY;
        }

        public void setDifficulty(int difficulty) {
            this.difficulty = difficulty;
        }
        
    }

    public static class ItemInfo {

        private int locX;
        private int locY;

        public ItemInfo(int locX, int locY) {
            this.locX = locX;
            this.locY = locY;
        }

        public int getLocX() {
            return locX;
        }

        public int getLocY() {
            return locY;
        }
    }
}
