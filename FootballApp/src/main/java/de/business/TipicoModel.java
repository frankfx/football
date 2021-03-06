package de.business;

import java.sql.Date;
import java.time.LocalDate;

import de.types.BetPredictionType;
import de.types.PersistenceType;

public class TipicoModel {
	private String mID;
	private String mTeam;
	private BetPredictionType mBetPrediction;
	private float mWinValue;
	private float mExpenses;
	private LocalDate mDate;
	private boolean mSuccess;
	private String mDescription;
	private PersistenceType mPersistenceType;

	public TipicoModel() {
		this(null, "Default", BetPredictionType.DRAW, 1.0f, 0.0f, LocalDate.now(), false, null, PersistenceType.NEW);
	}
	
	public TipicoModel(String pID){
		this.mID = pID;
	}
	
	public TipicoModel(String pID, String pTeam, BetPredictionType pPrediction, float pWinValue, float pExpenses, LocalDate pDate, boolean pSuccess, String pDescription){
		this(pID, pTeam, pPrediction, pWinValue, pExpenses, pDate, pSuccess, pDescription, PersistenceType.NEW);
	}
	
	public TipicoModel(String pID, String pTeam, BetPredictionType pPrediction, float pWinValue, float pExpenses, LocalDate pDate, boolean pSuccess, String pDescription, PersistenceType pPersistenceType){
		this.mID = pID;
		this.mTeam = pTeam;
		this.mBetPrediction = pPrediction;
		this.mWinValue = pWinValue;
		this.mExpenses = pExpenses;
		this.mDate = pDate;
		this.mSuccess = pSuccess;
		this.mDescription = pDescription;
		this.mPersistenceType = pPersistenceType;
	}
	
	public String getID() {
		return mID;
	}
	public void setID(String pID) {
		this.mID = pID;
	}
	public String getTeam() {
		return mTeam;
	}
	public void setTeam(String team) {
		this.mTeam = team;
	}
	public BetPredictionType getBetPrediction(){
		return mBetPrediction;
	}
	public void setBetPrediction(BetPredictionType pPrediction){
		this.mBetPrediction = pPrediction;
	}
	public float getWinValue() {
		return mWinValue;
	}
	public void setWinValue(float pWinValue) {
		this.mWinValue = pWinValue;
	}
	public float getExpenses() {
		return mExpenses;
	}
	public void setExpenses(float pExpenses) {
		this.mExpenses = pExpenses;
	}
	public LocalDate getDate() {
		return mDate;
	}
	public Date getSQLDate(){
		return Date.valueOf(mDate);
	}
	
	public void setDate(LocalDate lDate) {
		this.mDate = lDate;
	}
	
	public void setDate(Date lDate){
		this.mDate = lDate.toLocalDate();
	}
	
	public boolean getSuccess() {
		return mSuccess;
	}
	
	public void setSuccess(boolean lSuccess) {
		this.mSuccess = lSuccess;
	}
	
	public void setSuccessTrue(){
		this.mSuccess = true;
		setPersistenceType(PersistenceType.NEW);
	}
	
	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String pDescription) {
		this.mDescription = pDescription;
	}	
	
	public PersistenceType getPersistenceType() {
		return mPersistenceType;
	}
	
	public void setPersistenceType(PersistenceType pPersistenceType) {
		this.mPersistenceType = pPersistenceType;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.mID.equals(((TipicoModel)obj).getID());
	};
	
	@Override
	public String toString(){
		return "[" + mID + ", " + mTeam + ", " + mBetPrediction + ", " + mWinValue + ", " + mExpenses + ", " + mDate + ", " + mSuccess + "]" ;
	}
}
