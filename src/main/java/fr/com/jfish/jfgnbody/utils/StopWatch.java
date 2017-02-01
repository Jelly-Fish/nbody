package fr.com.jfish.jfgnbody.utils;

import fr.com.jfish.jfgnbody.exceptions.StopWatchNotStartedException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stop watch for calculating elapsed times between N events.
 * @author thw
 */
public class StopWatch { 

    //<editor-fold defaultstate="collapsed" desc="variables">
    /**
     * Started System current milliseconds time.
     */
    private long start;
    
    /**
     * Is started ?
     */
    private boolean started = false;
    
    /**
     * Maximum elapsed time defined by object using this object.
     */
    private double maxElapsedTime;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="constructor">
    /**
     * Constructor.
     * @param maxElapsedTime
     */
    public StopWatch(final double maxElapsedTime) {
        this.maxElapsedTime = maxElapsedTime;
        start();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="methods">
    /**
     * Start stop watch.
     */
    public final void start() {
         start = System.currentTimeMillis();
         started = true;
    }
    
    /**
     * Stop.
     */
    public void stop() {
        started = false;
    }
    
    /**
     * return time in seconds since this object was created.
     * @return elapsed time double.
     */
    private double elapsedTimeS() throws StopWatchNotStartedException {
        if (!started) {
            throw new StopWatchNotStartedException();
        } else {
            final long now = System.currentTimeMillis();
            return (now - start) / 1000.0;
        }
    } 
    
    /**
     * return time in milliseconds since this object was created.
     * @return elapsed time double.
     */
    private double elapsedTimeMS() throws StopWatchNotStartedException {
        if (!started) {
            throw new StopWatchNotStartedException();
        } else {
            final long now = System.currentTimeMillis();
            return (now - start);
        }
    } 
    
    /**
     * Use for time values in seconds.
     * Return boolean = MAX_ELAPSED_TIME greater than System elapsed time since
     * last call to StopWatch class start() method.
     * @return boolean MAX_ELAPSED_TIME greater than System elapsed time.
     */
    public boolean hasReachedMaxElapsedS() {
        
        if (!started) return false;
        
        try {
            return this.maxElapsedTime < elapsedTimeS();
        } catch (final StopWatchNotStartedException swex) {
            Logger.getLogger(StopWatch.class.getName()).log(Level.WARNING, null, swex);
            // If stop watch has not yet been started this method's use is obsolete.
            // Therefor return false to prevent any actions conditioned on this
            // method's return true statement.
            return false;
        }
    }
    
    /**
     * Use for time values in milliseconds.
     * Return boolean = MAX_ELAPSED_TIME greater than System elapsed time since
     * last call to StopWatch class start() method.
     * @return boolean MAX_ELAPSED_TIME greater than System elapsed time.
     */
    public boolean hasReachedMaxElapsedMS() {
        
        if (!started) return false;
        
        try {
            return this.maxElapsedTime < elapsedTimeMS();
        } catch (final StopWatchNotStartedException swex) {
            Logger.getLogger(StopWatch.class.getName()).log(Level.WARNING, null, swex);
            // If stop watch has not yet been started this method's use is obsolete.
            // Therefor return false to prevent any actions conditioned on this
            // method's return true statement.
            return false;
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="getter & setters">
    public double getMaxElapsedTime() {
        return maxElapsedTime;
    }

    public void setMaxElapsedTime(final double maxElapsedTime) {
        this.maxElapsedTime = maxElapsedTime;
    }
    //</editor-fold>
}