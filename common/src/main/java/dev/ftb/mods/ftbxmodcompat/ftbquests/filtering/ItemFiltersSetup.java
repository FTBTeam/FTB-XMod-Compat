package dev.ftb.mods.ftbxmodcompat.ftbquests.filtering;

import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;

class ItemFiltersSetup {
    public static void init() {
//        FTBQuestsAPI.api().registerFilterAdapter(new ItemFiltersAdapter());
        FTBXModCompat.LOGGER.info("[FTB Quests] Enabled Item Filters integration (STUB!)");
    }

//    private static class ItemFiltersAdapter implements ItemFilterAdapter {
//        @Override
//        public String getName() {
//            return "Item Filters";
//        }
//
//        @Override
//        public boolean isFilterStack(ItemStack stack) {
//            return ItemFiltersAPI.isFilter(stack);
//        }
//
//        @Override
//        public boolean doesItemMatch(ItemStack filterStack, ItemStack toCheck) {
//            return ItemFiltersAPI.isFilter(filterStack) && ItemFiltersAPI.filter(filterStack, toCheck);
//        }
//
//        @Override
//        public Matcher getMatcher(ItemStack filterStack) {
//            return new IFMatcher(filterStack);
//        }
//
//        @Override
//        public ItemStack makeTagFilterStack(TagKey<Item> tag) {
//            ItemStack tagFilter = new ItemStack(ItemFiltersItems.TAG.get());
//            ((IStringValueFilter) tagFilter.getItem()).setValue(tagFilter, tag.location().toString());
//            return tagFilter;
//        }
//    }
//
//    private static class IFMatcher implements ItemFilterAdapter.Matcher {
//        private final ItemStack filterStack;
//        private final IItemFilter filter;
//
//        private IFMatcher(ItemStack filterStack) {
//            this.filterStack = filterStack;
//            this.filter = ItemFiltersAPI.getFilter(filterStack);
//        }
//
//        @Override
//        public boolean test(ItemStack stack) {
//            return filter != null && filter.filter(filterStack, stack);
//        }
//    }
}
