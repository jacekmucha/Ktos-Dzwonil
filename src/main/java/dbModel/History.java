/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbModel;

import java.util.Date;

/**
 *
 * @author HP
 */
public class History {
    
    Date startCall;
    Date finishCall;
    Date duration;
    
    Date actualTime;
    
    Date dayOfCall;
    Date timeOfCall;

    public History(Date startCall, Date finishCall, Date duration, Date actualTime, Date dayOfCall, Date timeOfCall) {
        this.startCall = startCall;
        this.finishCall = finishCall;
        this.duration = duration;
        this.actualTime = actualTime;
        this.dayOfCall = dayOfCall;
        this.timeOfCall = timeOfCall;
    }

    
    public History() {
    }

    public Date getStartCall() {
        return startCall;
    }

    public void setStartCall(Date startCall) {
        this.startCall = startCall;
    }

    public Date getFinishCall() {
        return finishCall;
    }

    public void setFinishCall(Date finishCall) {
        this.finishCall = finishCall;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public Date getActualTime() {
        return actualTime;
    }

    public void setActualTime(Date actualTime) {
        this.actualTime = actualTime;
    }

    public Date getDayOfCall() {
        return dayOfCall;
    }

    public void setDayOfCall(Date dayOfCall) {
        this.dayOfCall = dayOfCall;
    }

    public Date getTimeOfCall() {
        return timeOfCall;
    }

    public void setTimeOfCall(Date timeOfCall) {
        this.timeOfCall = timeOfCall;
    }

    @Override
    public String toString() {
        return "History{" + "dayOfCall=" + dayOfCall + ", timeOfCall=" + timeOfCall + '}';
    }
    
    
    
    
}
