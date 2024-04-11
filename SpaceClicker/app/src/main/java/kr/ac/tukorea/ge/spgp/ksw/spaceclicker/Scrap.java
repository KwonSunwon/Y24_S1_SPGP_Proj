package kr.ac.tukorea.ge.spgp.ksw.spaceclicker;

public class Scrap implements GameObject {
    private long scrap;
    private long scrapPerTouch;
    private long scrapPerSecond;

    @Override
    public void Init() {
        // Load scrap from save file
        scrap = 0;
        scrapPerTouch = 1;
        scrapPerSecond = 0;
    }

    public void AddScrapFromClick() {
        scrap += scrapPerTouch;
    }

    public void AddScrapFromSecond() {
        scrap += scrapPerSecond;
    }

    public String GetScrap() {
        return Long.toUnsignedString(scrap);
    }
}
