package cn.gtcommunity.gregtinker.client.book;

import cn.gtcommunity.gregtinker.api.reflect.GTReflect;
import cn.gtcommunity.gregtinker.api.utils.collection.LazyAccum;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.mantle.client.book.repository.BookRepository;
import slimeknights.tconstruct.library.book.content.ContentListing;
import slimeknights.tconstruct.library.book.sectiontransformer.SectionTransformer;
import slimeknights.tconstruct.library.modifiers.Modifier;

public class BookTransformerAppendModifiers extends SectionTransformer
{
    private final BookRepository source;
    private final boolean armour;
    private final LazyAccum<Modifier> modCollector;

    public BookTransformerAppendModifiers(BookRepository source, boolean armour, LazyAccum<Modifier> modCollector) {
        super("modifiers");
        this.source = source;
        this.armour = armour;
        this.modCollector = modCollector;
    }

    @Override
    public void transform(BookData book, SectionData section) {
        ContentListing listing = (ContentListing)section.pages.get(0).content;
        for (Modifier mod : modCollector.collect()) {
            if ((!GTReflect.getItems(mod).isEmpty() /*|| ConArmHooks.INSTANCE.hasArmourModMatches(mod)*/)) {
                PageData page = new PageData();
                page.source = source;
                page.parent = section;
                page.type = armour ? "armormodifier" : "modifier";
                page.data = "modifiers/" + mod.identifier + ".json";
                section.pages.add(page);
                page.load();
                listing.addEntry(mod.getLocalizedName(), page);
            }
        }
    }
}
