# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

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
