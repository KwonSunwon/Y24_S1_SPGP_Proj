package kr.ac.tukorea.ge.spgp.ksw.spaceclicker.game.object;

import static kr.ac.tukorea.ge.spgp.ksw.spaceclicker.app.MainActivity.getContext;

import android.util.JsonReader;
import android.util.JsonWriter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class UpgradeInfo {
    public enum UPGRADE_TYPE {
        ANTENNA,
        ROBOT,
        ENGINE,
        CREW,
        RECYCLE,
        DESIGN;
    }

    private String name;
    private int initCost;
    private long cost;
    private long level;

    public static ArrayList<UpgradeInfo> load(){
        ArrayList<UpgradeInfo> upgradeInfos = new ArrayList<>();
        try{
            InputStream is = getContext().getAssets().open("upgradeInfo.json");
            JsonReader jr = new JsonReader(new InputStreamReader(is));
            jr.beginArray();
            while(jr.hasNext()){
                UpgradeInfo upgradeInfo = loadUpgradeInfo(jr);
                if(upgradeInfo != null){
                    upgradeInfos.add(upgradeInfo);
                }
            }
            jr.endArray();
            jr.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return upgradeInfos;
    }

    private static UpgradeInfo loadUpgradeInfo(JsonReader jr){
        UpgradeInfo upgradeInfo = new UpgradeInfo();
        try {
            jr.beginObject();
            while (jr.hasNext()) {
                String name = jr.nextName();
                if (name.equals("name")) {
                    upgradeInfo.name = jr.nextString();
                } else if (name.equals("initCost")) {
                    upgradeInfo.initCost = jr.nextInt();
                } else if (name.equals("cost")) {
                    upgradeInfo.cost = jr.nextLong();
                } else if (name.equals("level")) {
                    upgradeInfo.level = jr.nextLong();
                } else {
                    jr.skipValue();
                }
            }
            jr.endObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return upgradeInfo;
    }

    public static void save(ArrayList<UpgradeInfo> upgradeInfos){
        try{
            OutputStream os = getContext().openFileOutput("upgradeInfo.json", 0);
            JsonWriter jw = new JsonWriter(new OutputStreamWriter(os));
            jw.beginArray();
            for(UpgradeInfo upgradeInfo : upgradeInfos){
                saveUpgradeInfo(jw, upgradeInfo);
            }
            jw.endArray();
            jw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void saveUpgradeInfo(JsonWriter jw, UpgradeInfo upgradeInfo){
        try{
            jw.beginObject();
            jw.name("name").value(upgradeInfo.name);
            jw.name("initCost").value(upgradeInfo.initCost);
            jw.name("cost").value(upgradeInfo.cost);
            jw.name("level").value(upgradeInfo.level);
            jw.endObject();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public long getLevel(){
        return level;
    }

    public long getCost(){
        return cost;
    }

    public void upgrade(){
        level++;
        cost += cost / 2;
    }
}
