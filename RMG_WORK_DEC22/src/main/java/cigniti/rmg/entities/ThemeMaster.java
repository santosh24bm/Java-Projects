package cigniti.rmg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="themes_ui")
public class ThemeMaster implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6996791033940234432L;

	@Id
	@Column(name="theme_id")
	private int themeId ;
	
	@Column(name="theme_col")
	private String themeName;

	public int getThemeId() {
		return themeId;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeId(int themeId) {
		this.themeId = themeId;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	@Override
	public String toString() {
		return "ThemeMaster [themeId=" + themeId + ", themeName=" + themeName + "]";
	} 
	
	
	

}
