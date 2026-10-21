# Litematica Material Tracker

Live material list HUD overlay, shulker box inventory auto-auditor, and missing block alarms for Litematica on Minecraft Fabric (1.21.x).

## Features
- **Live HUD Overlay**: Real-time material checklist floating on your screen with progress tracking and 3D item icons.
- **Shulker Box & Container Auditor**: Automatically scans your player inventory, shulker boxes, bundles, and Ender Chest to calculate exact available stock.
- **Missing Block Alarms**: Audio alerts and visual highlights when entering placement zones with missing materials.
- **Cloth Config & Mod Menu Integration**: Full in-game GUI configuration screen factory supporting categorized settings, sliders, toggles, and tooltips.

## Configuration Guide & Tooltips

The configuration screen can be accessed directly in-game via Mod Menu or MaLiLib/Cloth Config hotkeys. Below is the documentation for all available configuration options:

### 1. Generic Settings
- **Enable Mod (`modEnabled`)**: Master switch for all Litematica Material Tracker features.
  * *Default*: `true`
  * *Tooltip*: Master toggle for all Litematica Material Tracker features.

### 2. HUD Overlay Settings
- **Enable HUD Overlay (`enableHud`)**: Renders the live material list HUD directly on screen.
  * *Default*: `true`
  * *Tooltip*: Renders the live material list HUD directly on screen.
- **HUD Position (`hudPosition`)**: Screen anchor point for the material list HUD overlay (e.g. Top Right, Top Left, Bottom Right, Bottom Left).
  * *Default*: `TOP_RIGHT`
  * *Tooltip*: Screen anchor point for the material list HUD overlay.
- **X Offset (`hudXOffset`)**: Horizontal offset in pixels from the anchor point.
  * *Default*: `8` (Range: 0 - 1000)
  * *Tooltip*: Horizontal offset in pixels from the anchor point.
- **Y Offset (`hudYOffset`)**: Vertical offset in pixels from the anchor point.
  * *Default*: `8` (Range: 0 - 1000)
  * *Tooltip*: Vertical offset in pixels from the anchor point.
- **HUD Scale (`hudScale`)**: Display scaling multiplier for HUD text and icons.
  * *Default*: `1.0` (Range: 0.5 - 3.0)
  * *Tooltip*: Display scaling multiplier for HUD text and icons.
- **Max Items Displayed (`maxItemsDisplayed`)**: Maximum number of material rows shown in the HUD.
  * *Default*: `12` (Range: 1 - 40)
  * *Tooltip*: Maximum number of material rows shown in the HUD.
- **Compact Mode (`compactMode`)**: Collapse details into a high-density summary HUD.
  * *Default*: `false`
  * *Tooltip*: Collapse details into a high-density summary HUD.
- **Sort Mode (`sortMode`)**: Ordering criteria for materials (Missing First, Total Count, Item Name).
  * *Default*: `MISSING_FIRST`
  * *Tooltip*: Ordering criteria: Missing First, Total Count, or Item Name.
- **Show Item Icons (`showItemIcons`)**: Render 3D item models next to material entries.
  * *Default*: `true`
  * *Tooltip*: Render 3D item models next to material entries.
- **Show Progress Bar (`showProgress_bar`)**: Display an overarching schematic completion progress bar.
  * *Default*: `true`
  * *Tooltip*: Display an overarching schematic completion progress bar.
- **Background Opacity (`backgroundOpacity`)**: Alpha transparency for the HUD backdrop.
  * *Default*: `0.75` (Range: 0.0 - 1.0)
  * *Tooltip*: Alpha transparency for the HUD backdrop.

### 3. Shulker Auditor Settings
- **Audit Shulker Boxes (`enableShulkerAudit`)**: Inspect all shulker boxes inside inventory and sum nested items.
  * *Default*: `true`
  * *Tooltip*: Inspect all shulker boxes inside inventory and sum nested items.
- **Audit Ender Chest (`enableEnderChestAudit`)**: Inspect player Ender Chest contents and nested shulkers.
  * *Default*: `true`
  * *Tooltip*: Inspect player Ender Chest contents and nested shulkers.
- **Audit Bundles (`auditBundleContents`)**: Inspect bundle contents in player inventory and ender chest.
  * *Default*: `true`
  * *Tooltip*: Inspect bundle contents in player inventory and ender chest.
- **Recursive Nested Scanning (`recursiveContainerScan`)**: Scan nested containers inside bundles or shulkers up to safe depth.
  * *Default*: `true`
  * *Tooltip*: Scan nested containers inside bundles or shulkers up to safe depth.
- **Audit Interval (`auditIntervalTicks`)**: Frequency of background inventory audits in game ticks.
  * *Default*: `20 ticks` (1 second, Range: 1 - 200)
  * *Tooltip*: Frequency of background inventory audits (20 ticks = 1 second).

### 4. Alarms & Warnings
- **Enable Missing Block Alarm (`enableMissingAlarm`)**: Play audio alarm when entering placement zone without required materials.
  * *Default*: `true`
  * *Tooltip*: Play audio alarm when entering placement zone without required materials.
- **Alarm Sound (`alarmSound`)**: Sound event played when missing material alert triggers.
  * *Default*: `PLING`
  * *Tooltip*: Sound event played when missing material alert triggers.
- **Alarm Volume (`alarmVolume`)**: Volume level for missing item alerts.
  * *Default*: `0.8` (Range: 0.0 - 1.0)
  * *Tooltip*: Volume level for missing item alerts.
- **Alarm Pitch (`alarmPitch`)**: Pitch multiplier for missing item alerts.
  * *Default*: `1.0` (Range: 0.5 - 2.0)
  * *Tooltip*: Pitch multiplier for missing item alerts.
- **Alarm Cooldown (`alarmCooldownSeconds`)**: Minimum seconds between repeated sound alarms.
  * *Default*: `3` seconds (Range: 1 - 60)
  * *Tooltip*: Minimum seconds between repeated sound alarms.
- **Low Stock Threshold (`warnLowThresholdPercent`)**: Trigger warning when available stock is below this percentage.
  * *Default*: `15%` (Range: 1 - 100)
  * *Tooltip*: Trigger warning when available stock is below this percentage.

## License
MIT License. See [LICENSE](file:///D:/GEMINI%20AGY/workspaces/litematica-material-tracker/LICENSE).
