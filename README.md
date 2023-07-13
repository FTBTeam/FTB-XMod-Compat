# FTB-XMod-Compat

This is a compatibility mod whose only purpose is to provide cross-mod integration between FTB and non-FTB mods. It does nothing on its own, but adds mod integration based on what other mods it detects.

All mod dependencies (both FTB and non-FTB) are soft dependencies. So this mod will start up if no other mods are present, but won't do anything useful.

## FTB Quests

The following cross-mod integrations are added to FTB Quests, if present:

### KubeJS

* FTB Quests fires KubeJS events when certain things occur
* FTB Quests uses KubeJS as its game stages implementation

### Game Stages

If Game Stages is present, and KubeJS is _not_ present:

* FTB Quests uses Game Stages as its game stages implementation
* FTB Quests will respond to events emitted by Game Stages (stages added/remove/edited) by re-checking any existing stage tasks for completion

### REI / JEI

If either REI or JEI is present:

* FTB Quests will use that mod for displaying "recipes" for item tasks with item rewards
* FTB Quests will use that mod for displaying the items obtained from any configured loot crates (rewards from the reward table which have loot crates enabled)
* FTB Quests will use that mod to show the recipes for items in item tasks (as viewed in the quest display panel)

### Fallbacks

If neither KubeJS nor Game Stages is loaded, FTB Quests will use an inbuilt stages implementation based on string "tags" which can be added to players. This works but is extremely limited in functionality (either a player has a given tag, or doesn't).

## FTB Chunks

TODO, FTB Chunks not supported yet but will be in a future release
