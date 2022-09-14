package au.edu.curtin.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Maintains the overall dataset; specifically of course all the different factions.
 */
public class FactionList
{
    private List<Faction> factions = new ArrayList<>();
    private FactionDBHelper db;


    public FactionList() {}

    public void load(Context context)
    {
        this.db = new FactionDBHelper(context);
    }

    public int size()
    {
        return factions.size();
    }

    public Faction get(int i)
    {
        return factions.get(i);
    }

    public int add(Faction newFaction)
    {
        factions.add(newFaction);
        db.insertFaction(newFaction);

        return factions.size() - 1; // Return insertion point
    }

    public void edit(Faction newFaction)
    {
        db.updateFaction(newFaction);
    }

    public void remove(Faction rmFaction)
    {
        factions.remove(rmFaction);
        db.deleteFaction(rmFaction.getId());
    }
}
