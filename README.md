# FTB-XMod-Compat

This is a compatibility mod whose only purpose is to provide cross-mod integration between FTB and non-FTB mods. It does nothing on its own, but adds mod integration based on what other mods it detects.

All mod dependencies (both FTB and non-FTB) are soft dependencies. So this mod will start up if no other mods are present, but won't do anything useful.

## FTB Quests

The following cross-mod integrations are added to FTB Quests 1902.5.0+, if present:

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

### Item Filters and FTB Filter System

As of version 2.1.0, FTB XMod Compat provides FTB Quests integration for the Item Filters and FTB Filter System mods. Item Filters was previously a hard dependency for FTB Quests, but is now an optional dependency, along with the new FTB Filter System mod. Using FTB Filter System will be the recommended path for future modpacks.

With KubeJS installed, FTB Filter System's "Custom" filter fires a KubeJS event allowing pack developers to match items using an external KJS script. See https://github.com/FTBTeam/FTB-Filter-System/blob/main/README.md for more information on this, including examples.

### Fallbacks

If neither KubeJS nor Game Stages is loaded, FTB Quests will use an inbuilt stages implementation based on string "tags" which can be added to players. This works but is extremely limited in functionality (either a player has a given tag, or doesn't).

If neither Item Filters nor FTB Filter System is load, only simple item matching will be available for FTB Quests item tasks.

## FTB Chunks

The following cross-mod integrations are added to FTB Chunks 1902.4.0+, if present:

### KubeJS

* FTB Chunks fires KubeJS events when certain things occur
* FTB Chunks uses KubeJS as its game stages implementation

### Game Stages

If Game Stages is present, and KubeJS is _not_ present:

* FTB Chunks uses Game Stages as its game stages implementation (the stage `ftbchunks_mapping` is required to use the minimap or fullscreen map)

### Waystones

If the Waystones mod is present:

* FTB Chunks will display waystones known to the player as icons on the minimap and fullscreen map

### FTB Ranks -or- Luckperms

If FTB Ranks and/or Luckperms are present:

* FTB Chunks will use one of these mods (FTB Ranks if both are present) to configure the following values (overriding server defaults):
  * `ftbchunks.max_claimed` - max chunks a player may claim
  * `ftbchunks.max_force_loaded` - max chunks a player may forceload
  * `ftbchunks.chunk_load_offline` - whether force-loaded chunks stay force loaded while the player is offline
  * `ftbchunks.no_wilderness` - whether the player may build (place or break) in unclaimed chunks
