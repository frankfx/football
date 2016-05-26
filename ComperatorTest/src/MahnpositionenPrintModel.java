import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MahnpositionenPrintModel {

	private static final String MAHNBELEGART = "mahnbelegart";
	private static final String TYP = "typ";

	private Sortierreihenfolge mSortierreihenfolge = Sortierreihenfolge.MAHNBELEGART;

	private Mahnung mMahnung;

	public MahnpositionenPrintModel(Mahnung pMahnung) {
		mMahnung = pMahnung;
	}
	
	
	public static enum Sortierreihenfolge {
		MAHNBELEGART, TYP;
	}


	public Iterator<ForderungVerbindlichkeitModel> retrieveListDetailIterator() {
		Iterator<ForderungVerbindlichkeitModel> result = null;
		List<ForderungVerbindlichkeitModel> lForderungen = new ArrayList<ForderungVerbindlichkeitModel>(this.getForderungenFuerZuDruckendePositionen(mMahnung));
		
		final Comparator<ForderungVerbindlichkeitModel> lCompositeComparator;
		
		final Comparator<ForderungVerbindlichkeitModel> lTypComparator = new Comparator<ForderungVerbindlichkeitModel>() {
			public int compare(final ForderungVerbindlichkeitModel o1, final ForderungVerbindlichkeitModel o2) {
				boolean o1IstBescheidet = o1.hatBescheideteMahnung();
				boolean o2IstBescheidet = o2.hatBescheideteMahnung();
			
				int result = 0;

				// sortiere offene (nicht bescheidete) nach vorn
				if (!o1IstBescheidet && o2IstBescheidet) {
					result = -1;
				} else if (o1IstBescheidet && !o2IstBescheidet) {
					result = 1;
				}

				boolean o1IstSZVZUndHatMahnungen = (o1.getMahnbelegart().equals(MahnBelegartType.SAEUMNISZUSCHLAG) || o1.getMahnbelegart().equals(MahnBelegartType.VERSPAETUNGSZUSCHLAG))
						&& !o1.getMahnungen().isEmpty();
				boolean o2IstSZVZUndHatMahnungen = (o2.getMahnbelegart().equals(MahnBelegartType.SAEUMNISZUSCHLAG) || o2.getMahnbelegart().equals(MahnBelegartType.VERSPAETUNGSZUSCHLAG))
						&& !o2.getMahnungen().isEmpty();

				if (o1IstSZVZUndHatMahnungen && o2IstSZVZUndHatMahnungen) {
					result = 0;
				} else if (o1IstSZVZUndHatMahnungen) {
					result = 1;
				} else if (o2IstSZVZUndHatMahnungen) {
					result = -1;
				}

				return result;
			}
		};
		lCompositeComparator = new CompositeComparator<ForderungVerbindlichkeitModel>(
				lTypComparator,
				CompositeComparator.sFORDERUNGVERBINDLICHKEIT_MAHNBELEGART_COMPARATOR);

		java.util.Collections.sort(lForderungen, lCompositeComparator);
		result = lForderungen.iterator();
		// USER SECTION END retrieveListDetailIterator~void
		// @formatter:off
		return result;
	}

	protected Collection<ForderungVerbindlichkeitModel> getForderungenFuerZuDruckendePositionen(Mahnung m) {
		return m.getAlleForderungenUndGebuehrenZurMahnung();
	}
}