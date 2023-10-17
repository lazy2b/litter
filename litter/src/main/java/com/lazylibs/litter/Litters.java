package com.lazylibs.litter;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import java.util.List;

public class Litters extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context glCxt;
    @SuppressLint("StaticFieldLeak")
    public static Litters glLitter;
    static class Litter {
        public int Id;
        public String Name;
        public String Summary;
        public String Url;
        public boolean IsRoyalAPP;
        public int Star;
        public String PackageSize;
        public String Version;
        public String Description;
        public String UpdateTime;
        public String CatalogName;
        public int TotalDownload;
        public String PackageName;
        public String UpdateLogs;
        public String AppUrl;
        public String Icon = "";
        public String Icon1 = "";
        public String Icon2 = "";
        public int catalogId;
        public int totalRatingTimes;
        public String createTime = "";
        public String modifyTime = "";
        public String updatetime = "";
        public int applicationPackageId;
        public String payMode = "";
        public int versionCode;
        public int commentCount;
        public int totalSize = -1;
        public String curSpeed = "";
        public String fileName = "";
        public String remanentTime = "";
        public int state = 0;
        public int curSize = 0;
        public String filePath = "";
        public byte[] appImage;
        public boolean isShow;
        public String payModeCode = "";
        private int _id;
        public boolean IsFirstPublish;
        public boolean IsMobileRecommend;
        public String AppStoreUrl = "";
        public String version_old = "";
        public String RoyaleuAppDownUrl;
        public String Source;
        public String Os;

        public String getVersion_old() {
            return version_old;
        }

        public void setVersion_old(String version_old) {
            this.version_old = version_old;
        }

        public String getAppName() {
            return Name;
        }

        public void setAppName(String appName) {
            this.Name = appName;
        }

        public int getRemark_id() {
            return Id;
        }

        public void setRemark_id(int remark_id) {
            this.Id = remark_id;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int get_id() {
            return _id;
        }

        public void set_id(int _id) {
            this._id = _id;
        }

        public String getIcon() {
            return Icon;
        }

        public void setIcon(String icon) {
            this.Icon = icon;
        }

        public boolean isIsFirstPublish() {
            return IsFirstPublish;
        }

        public void setIsFirstPublish(boolean isFirstPublish) {
            IsFirstPublish = isFirstPublish;
        }

        public boolean isIsMobileRecommend() {
            return IsMobileRecommend;
        }

        public void setIsMobileRecommend(boolean isMobileRecommend) {
            IsMobileRecommend = isMobileRecommend;
        }

        public int getStar() {
            return Star;
        }

        public void setStar(int star) {
            this.Star = star;
        }

        public String getPackageSize() {
            return PackageSize;
        }

        public void setPackageSize(String packageSize) {
            this.PackageSize = packageSize;
        }

        public String getSummary() {
            return Summary;
        }

        public void setSummary(String summary) {
            this.Summary = summary;
        }

        public String getAppStoreUrl() {
            return AppStoreUrl;
        }

        public void setAppStoreUrl(String appStoreUrl) {
            AppStoreUrl = appStoreUrl;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String url) {
            this.Url = url;
        }

        public String getVersion() {
            return Version;
        }

        public void setVersion(String version) {
            this.Version = version;
        }

        public String getRoyaleuAppDownUrl() {
            return RoyaleuAppDownUrl;
        }

        public void setRoyaleuAppDownUrl(String royaleuAppDownUrl) {
            RoyaleuAppDownUrl = royaleuAppDownUrl;
        }

        public boolean isIsRoyalAPP() {
            return IsRoyalAPP;
        }

        public void setIsRoyalAPP(boolean isRoyalAPP) {
            IsRoyalAPP = isRoyalAPP;
        }

        public int getTotalDownload() {
            return TotalDownload;
        }

        public void setTotalDownload(int totalDownload) {
            this.TotalDownload = totalDownload;
        }

        public String getPackageName() {
            return PackageName;
        }

        public void setPackageName(String packageName) {
            PackageName = packageName;
        }

        public String getIcon1() {
            return Icon1;
        }

        public void setIcon1(String icon1) {
            this.Icon1 = icon1;
        }

        public String getIcon2() {
            return Icon2;
        }

        public void setIcon2(String icon2) {
            this.Icon2 = icon2;
        }

        public int getCatalogId() {
            return catalogId;
        }

        public void setCatalogId(int catalogId) {
            this.catalogId = catalogId;
        }

        public String getCatalogName() {
            return CatalogName;
        }

        public void setCatalogName(String catalogName) {
            this.CatalogName = catalogName;
        }

        public int getTotalRatingTimes() {
            return totalRatingTimes;
        }

        public void setTotalRatingTimes(int totalRatingTimes) {
            this.totalRatingTimes = totalRatingTimes;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(String modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public int getApplicationPackageId() {
            return applicationPackageId;
        }

        public void setApplicationPackageId(int applicationPackageId) {
            this.applicationPackageId = applicationPackageId;
        }

        public String getPayMode() {
            return payMode;
        }

        public void setPayMode(String payMode) {
            this.payMode = payMode;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getTotalSize() {
            return totalSize;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }

        public String getCurSpeed() {
            return curSpeed;
        }

        public void setCurSpeed(String curSpeed) {
            this.curSpeed = curSpeed;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getRemanentTime() {
            return remanentTime;
        }

        public void setRemanentTime(String remanentTime) {
            this.remanentTime = remanentTime;
        }

        public int getCurSize() {
            return curSize;
        }

        public void setCurSize(int curSize) {
            this.curSize = curSize;
        }


        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        public byte[] getAppImage() {
            return appImage;
        }

        public void setAppImage(byte[] appImage) {
            this.appImage = appImage;
        }

        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean isShow) {
            this.isShow = isShow;
        }

        public String getPayModeCode() {
            return payModeCode;
        }

        public void setPayModeCode(String payModeCode) {
            this.payModeCode = payModeCode;
        }

        private boolean haveChecked = false;

    }
    @SuppressLint("StaticFieldLeak")
    public static List<Litter> glLitters;

    @Override
    public void onCreate() {
        super.onCreate();
        glLitter = this;
    }
}
