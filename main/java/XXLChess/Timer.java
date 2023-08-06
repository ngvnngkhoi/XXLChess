package XXLChess;
/**
 * Used to outline the functionality of the timer.
 */
public class Timer {
    long total;
    long start;
    int increment;
    long pausedTime;
    boolean isPaused;

    public Timer(int time, int increment) {
        this.total = time * 1000;
        this.start = System.currentTimeMillis();
        this.increment = increment * 1000;
    }
    /**
     * Creates a timer with a given time and increment.
     * @param time Time in seconds.
     * @param increment Increment in seconds.
     */

    public long remaining() {
        if(isPaused)
        {
            return total;
        }
        else
        {
        return total - (System.currentTimeMillis() - start);
        }
    }
    /**
     * @return Remaining time in milliseconds.
     */

    public void addIncrement() {
        total += increment;
    }
    /**
     * Adds increment to total time.
     */

    public void setPaused()
    {
        isPaused = true;
    }
    /**
     * Pauses the timer.
     */


    public void setUnpaused()
    {
        isPaused = false;
    }
    /**
     * Unpauses the timer.
     */

    public boolean isOver() {
        return remaining() <= 0;
    }
    /**
     * Used to determine the endgame state where one entity runs out of time before checkmate, stalemate, draw or resignation.
     * @return True if the timer is over, false if not.
     */

    public void resumeTime() {
        start = System.currentTimeMillis();
        total = pausedTime;
        pausedTime = 0;
    }
    /**
     * Resumes the timer.
     */

    public void reset() {
        start = System.currentTimeMillis();
    }
    /**
     * Resets the timer.
     */

    public String toString() {
        long time = remaining() / 1000;
        return String.format("%02d:%02d", time / 60, time % 60);
    }
    /**
     * Used to redner the timer onto the screen.
     * @return Remaining time in minutes and seconds.
     */
}