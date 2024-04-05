package algonquin.cst2335.pajaappfinal;

/**
 * Interface for handling RecyclerView item clicks.
 * Implement this interface in activities or fragments to handle item clicks in RecyclerViews.
 *
 * <p>
 * -------------------------------------------------------
 * Course: CST 2335 - Mobile Graphical Interface Programming
 * Final Project: Deezer Song Search API
 * Student Name: Allan Torres
 * Student Number: 041022473
 * -------------------------------------------------------
 * </p>
 */
public interface IRecyclerView {
    /**
     * Called when an item in the RecyclerView is clicked.
     *
     * @param position The position of the clicked item in the RecyclerView.
     */
    void onItemClick(int position);
}
