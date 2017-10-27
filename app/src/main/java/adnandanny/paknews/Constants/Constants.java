package adnandanny.paknews.Constants;

/**
 * Created by Adnan Danny on 9/14/2017.
 */

public class Constants {

    public static final String BBC_PAKISTAN = "http://www.bbc.com/urdu/pakistan";
    private String titlepakistan;
    private String descriptionpakistan;
    private String datepakistan;
    private String postlinkpakistan;
    private String picspakistan;

    public Constants() {
    }

    public Constants(String titlepakistan, String descriptionpakistan, String date, String postlinkpakistan, String picspakistan) {
        this.titlepakistan = titlepakistan;
        this.descriptionpakistan = descriptionpakistan;
        this.datepakistan = date;
        this.postlinkpakistan = postlinkpakistan;
        this.picspakistan = picspakistan;
    }

    public String getTitlepakistan() {
        return titlepakistan;
    }

    public void setTitlepakistan(String titlepakistan) {
        this.titlepakistan = titlepakistan;
    }

    public String getDescriptionpakistan() {
        return descriptionpakistan;
    }

    public void setDescriptionpakistan(String descriptionpakistan) {
        this.descriptionpakistan = descriptionpakistan;
    }

    public String getDatepakistan() {
        return datepakistan;
    }

    public void setDatepakistan(String datepakistan) {
        this.datepakistan = datepakistan;
    }

    public String getPostlinkpakistan() {
        return postlinkpakistan;
    }

    public void setPostlinkpakistan(String postlinkpakistan) {
        this.postlinkpakistan = postlinkpakistan;
    }

    public String getPicspakistan() {
        return picspakistan;
    }

    public void setPicspakistan(String picspakistan) {
        this.picspakistan = picspakistan;
    }
}
