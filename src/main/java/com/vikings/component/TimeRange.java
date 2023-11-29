package com.vikings.component;

public class TimeRange implements Comparable<TimeRange> {

  private int timeStart;
  private int timeEnd;

  private int startHour;
  private int startMin;
  private int endHour;
  private int endMin;
  private int visitors;

  
  public TimeRange() {
    startHour = 0;
    startMin = 0;
    endHour = 0;
    endMin = 0;
    timeStart = 0;
    timeEnd = 0;
    visitors = 0;
  }
  
  public void setStartHour(int startHour)
  {
  	this.startHour = startHour;

  	this.timeStart = this.startHour * 60 + this.startMin;
  }

  public int getStartHour()
  {
      return (this.startHour);
  }

  public void setStartMinute(int startMin)
  {
  	this.startMin = startMin;
  	this.timeStart = this.startHour * 60 + this.startMin;
  }

  public int getStartMinute()
  {
      return (this.startMin);
  }

  public void setEndHour(int endHour)
  {
  	this.endHour = endHour;
  	this.timeEnd = this.endHour * 60 + this.endMin;
  }

  public int getEndHour()
  {
      return (this.endHour);
  }

  public void setEndMinute(int endMin)
  {
  	this.endMin = endMin;
  	this.timeEnd = this.endHour * 60 + this.endMin;
  }

  public int getEndMinute()
  {
      return (this.endMin);
  }


  public void setStartTime(int time)
  {
  	this.timeStart = time;

  	this.startHour = this.timeStart / 60;
  	this.startMin = this.timeStart % 60;

  }

  public int getStartTime()
  {
      return (this.timeStart);
  }

  public void setEndTime(int time)
  {
  	this.timeEnd = time;

  	this.endHour = this.timeEnd / 60;
  	this.endMin = this.timeEnd % 60;
  }

  public int getEndTime()
  {
      return (this.timeEnd);
  }


  public void setVisitors(int visitors)
  {
  	this.visitors = visitors;
  }

  public int getVisitors()
  {
      return (this.visitors);
  }


  public void Clear()
  {
      startHour = 0;
      startMin = 0;
      endHour = 0;
      endMin = 0;
      timeStart = 0;
      timeEnd = 0;
      visitors = 0;
  }

  
  public int compareTo(TimeRange tr) {
  	
    int cmp = (tr.getVisitors() - getVisitors());
  	
    if (cmp == 0)
    {
        cmp = (tr.getStartTime() - getStartTime());
        if (cmp == 0)
            cmp = (tr.getEndTime() - getEndTime());
    }

    return (cmp);
  }
  
}
