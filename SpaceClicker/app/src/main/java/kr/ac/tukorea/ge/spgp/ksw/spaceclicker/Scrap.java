package kr.ac.tukorea.ge.spgp.ksw.spaceclicker;

public class Scrap implements GameObject {
    private long scrap;
    private long scrapPerTouch;
    private long scrapPerSecond;

    public void Init() {
        // Load scrap from save file
        scrap = 0;
        scrapPerTouch = 1;
        scrapPerSecond = 0;
    }

    @Override
    public void Update() {
        scrap += scrapPerSecond;
    }

    @Override
    public void Render() {

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
