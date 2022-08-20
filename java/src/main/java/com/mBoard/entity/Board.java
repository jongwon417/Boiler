package com.mBoard.entity;

import java.time.LocalDateTime;

public class Board {
	
	private int seqno;
	private String userid;
	private String mwriter;
	private String mtitle;
	private LocalDateTime mregdate;
	private String mcontent;
	
	/* Getter & Setter */
	public int getSeqno() {
		return seqno;
	}
	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMwriter() {
		return mwriter;
	}
	public void setMwriter(String mwriter) {
		this.mwriter = mwriter;
	}
	public String getMtitle() {
		return mtitle;
	}
	public void setMtitle(String mtitle) {
		this.mtitle = mtitle;
	}
	public LocalDateTime getMregdate() {
		return mregdate;
	}
	public void setMregdate(LocalDateTime mregdate) {
		this.mregdate = mregdate;
	}
	public String getMcontent() {
		return mcontent;
	}
	public void setMcontent(String mcontent) {
		this.mcontent = mcontent;
	}
	
	

}
