# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [3.2.0]

### Changed
* Ported to Minecraft 1.21. Support for Fabric and NeoForge.
  * Forge support may be re-added if/when Architectury adds support for Forge

## [3.1.0]

### Changed
* Ported to Minecraft 1.20.6. Support for Fabric and NeoForge.
  * Forge support may be re-added if/when Architectury adds support for Forge

## [3.0.3]

### Fixed
* Moved some cross-mod init code to later in the mod startup

## [3.0.2]

### Changed
* Ported to Minecraft 1.20.4. Supports Forge, NeoForge and Fabric.
  * Integration is not available for KubeJS or Gamestages, since those mods are not yet releases on 1.20.4

## [2.1.0]

### Added
* Added FTB Quests integration for Item Filters and FTB Filter System mods
  * FTB Quests 2001.3.0 now required; it now supports external filtering mods via an abstraction layer
  * Item Filter is no longer a hard dependency for FTB Quests; it's supported along with FTB Filter System by FTB XMod Compat

## [2.0.4]

### Fixed
* Fixed FTB Ranks / Luckperms integration not being picked  up correctly due to bug introduced in 2.0.2

## [2.0.3]

### Fixed
* (Fabric) Fixed crash when Common Protection API is loaded and FTB Chunks is not loaded

## [2.0.2]

### Added
* Basic KJS/FTB Teams integration; fire events when a player joins or leaves a team
* Added configurability for backend implementations of stages and permissions mods

## [2.0.1]

### Added
* Added back KubeJS support for FTB Quests and FTB Chunks
  * Note: not well tested and currently based on the alpha release of KubeJS for 1.20.1, so stability not guaranteed
* Better dynamic support for FTB Quests Loot Crates in JEI - crates are now added and removed as soon as a reward table has a loot crate enabled/disabled via edit mode
  * REI support for this is pending
  * Note: FTB Quests 2001.1.3 or later required

## [2.0.0]

### Added
* Ported to Minecraft 1.20.1
  * Functionally equivalent to last 1.19.2 release except KubeJS integration (no KubeJS for 1.20.1 at this time)
  * Will support FTB Quests cross-mod compat when there is a public FTB Quests release for 1.20.1

## [1.2.1]

### Fixed
* Fixed a crash when Waystones is loaded and FTB Chunks isn't loaded.

## [1.2.0]

### Added
* Now handles cross-mod compat for FTB Chunks
  * FTB Chunks 1902.4.0 or later required; all cross-mod compat has been moved from FTB Chunks to FTB XMod Compat
  * Mod support: KubeJS (6.1), Game Stages (Forge only), Waystone (Forge/Fabric), Common Protection API (Fabric only)
  
## [1.1.2]

### Fixed
* Fixed crash when Game Stages is loaded, and KubeJS is not loaded.

## [1.1.1]

### Fixed
* Fixed KubeJS version not being liked on Fabric

## [1.1.0]

### Changed
* Updated to support KubeJS 6.1 integration with FTB Quests
  * This release does _not_ work with KubeJS 6.0

## [1.0.0]

### Added
* Initial release
  * Adds REI, JEI, KubeJS and Game Stages integration for FTB Quests
  * Supports KubeJS 6.0; this release does _not_ work with KubeJS 6.1
