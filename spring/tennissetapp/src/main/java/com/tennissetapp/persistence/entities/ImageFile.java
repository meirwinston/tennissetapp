package com.tennissetapp.persistence.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

@NamedQueries({
//	@NamedQuery(
//	    name="ImageFile.selectByAccountAndFolder",
//	    query="SELECT o FROM ImageFile AS o WHERE o.ownerEmail = :email AND o.folderName = :folderName ORDER BY o.createdOn DESC"
//	),
//	@NamedQuery(
//	    name="ImageFile.selectAlbums",
//	    query="SELECT DISTINCT i.album FROM ImageFile AS i WHERE i.ownerEmail = :email"
//	),
//	@NamedQuery(
//	    name="ImageFile.countByOwnerAlbumFolder",
//	    query="SELECT COUNT(i.imageFileId) FROM ImageFile AS i WHERE i.ownerEmail = :email AND i.album = :album AND i.folderName = :folderName"
//	),
//	@NamedQuery(
//	    name="ImageFile.scrollIDsByOwnerAlbumFolder",
//	    query="SELECT i.imageFileId FROM ImageFile AS i WHERE i.ownerEmail = :email AND i.album = :album AND i.folderName = :folderName ORDER BY i.createdOn DESC"
//	)
})
@Table(name="image_files",schema="tennissetapp")
@Entity
public class ImageFile{
	public interface Album{
		String DEFAULT = "Default";
	}
	
	public enum SystemFolder{
		USER_UPLOADS,
		PROFILE_PHOTOS,
		TENNIS_COURTS,
		THUMBNAILS,
		GARBAGE
	}
	public enum Format{
		JPG,
		PNG,
		JPEG,
		GIF
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long imageFileId;
	
	protected String fileName;
	
	@Column(nullable=false)
	protected Date createdOn;
	
	protected String dirPath;
	
	protected String album;
	
	protected Long ownerId;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="thumbnailId",referencedColumnName="thumbnailId",updatable=false, insertable=false)
//	protected ImageFile thumbnail;
	
	@Column(name="width")
	protected Integer width;
	
	@Column(name="height")
	protected Integer height;
	
	@Column(name="size")
	protected Long size;
	
	@Enumerated(value=EnumType.STRING)
	@Column(name="format")
	protected Format format;
	
	public Long getImageFileId() {
		return imageFileId;
	}

	public void setImageFileId(Long imageFileId) {
		this.imageFileId = imageFileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getWidth() {
		return width;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}
	public String getDirPath() {
		return dirPath;
	}

	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	@Override
	public String toString() {
		return "ImageFile [imageFileId=" + imageFileId + ", fileName="
				+ fileName + ", createdOn=" + createdOn + ", dirPath="
				+ dirPath + ", album=" + album + ", ownerId=" + ownerId
				+ ", width=" + width + ", height=" + height + ", size=" + size
				+ ", format=" + format + "]";
	}

	

//	public String resolveFullPath(){
//		String fullName = Utils.getProperty("branchitup.rootDir") + "/" + Utils.getProperty("branchitup.diskresources.imagesPath") + "/" + this.folderName + "/" + this.fileName;
//		return fullName;
//	}
//
//	public boolean delete(){
//		boolean deleted = false;
//		String fullName = resolveFullPath();//Utils.getProperty("branchitup.rootDir") + "/" + Utils.getProperty("branchitup.diskresources.imagesPath") + "/" + this.folderName + "/" + this.fileName;
//		String thumbnailFullName = Utils.getProperty("branchitup.rootDir") + "/" + Utils.getProperty("branchitup.diskresources.imagesPath") + "/" + this.folderName + "/thumbnail_" + this.fileName;
//		
//		File file = new File(fullName);
//		System.out.println("ImageFile.delete: " + file.getAbsolutePath() + ", " + file.exists());
//		if(file.exists()){
//			deleted = file.delete();
//			System.out.println(file.getAbsolutePath() + "(id: " + this.imageFileId + ")" + " deleted? " + deleted);
//		}
//		else{
//			System.out.println("FileSystem.DID NOT delete: " + fullName + "(" + this.imageFileId + ")");
//		}
//		file = new File(thumbnailFullName);
//		System.out.println("ImageFile.thumbnail.delete: " + thumbnailFullName);
//		if(file.exists()){
//			deleted = file.delete();
//			System.out.println(thumbnailFullName + "(thumbnail id: " + this.imageFileId + ")" + " deleted? " + deleted);
//		}
//		else{
//			System.out.println("Thumbnail FileSystem.DID NOT delete: " + thumbnailFullName);
//		}
//		return deleted;
//	}

	

	
}
