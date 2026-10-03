# Smart Reordering

## Module Overview

Smart Reordering is a module of an Inventory Management Software system that determines when inventory should be replenished, how much should be replenished, and where that replenishment should come from.

The module uses current inventory, historical and expected demand, supplier information, inventory policies, and purchasing constraints to produce replenishment recommendations. It also provides monitoring functions that allow reorder settings and supplier performance to be reviewed using actual inventory and fulfillment results.

The features are designed to operate together: demand and inventory information provide the inputs for reorder calculations; supplier and safety-stock information account for replenishment uncertainty; reorder monitoring identifies when action is required; sourcing and quantity functions determine how the requirement should be fulfilled; constraint and budget checks validate the recommendation; and purchasing and monitoring functions complete the process and provide feedback for future decisions.

---

## 1. Current Stock

### Purpose

Current Stock provides the Smart Reordering process with the quantity of each product currently available in inventory.

### Function

The feature determines the current inventory level of an item using the item's inventory records and relevant transaction information.

### Information Required

- Product or SKU
- Current inventory records
- Sales history
- Order history
- Location, where applicable
- Par level or target stock information, where maintained

### Process

1. Identify the product using its product or SKU identifier.
2. Retrieve the item's inventory record.
3. Check relevant sales and order transactions.
4. Determine the quantity currently available.
5. Return the current stock position to the reordering process.

### Output / Action

- Current quantity available for the item
- Inventory position used by other Smart Reordering features

### Dependencies

Current Stock provides basic inventory information to Demand Forecasting, Reorder Point Monitoring, Stockout Risk Monitoring, Replenishment Quantity Recommendation, and other features that require current inventory data.

---

## 2. Demand Forecasting

### Purpose

Demand Forecasting determines the expected demand for an item from its historical sales or demand records.

### Function

The feature maintains an item-specific estimate of average daily demand and updates it as new demand information becomes available.

### Information Required

- Item or SKU
- Location, where applicable
- Historical daily sales or demand
- Current demand estimate
- Forecasting parameters

### Process

1. Record the item's actual daily demand.
2. Update the item's demand estimate using exponential smoothing.
3. Maintain an item-specific smoothing parameter.
4. Recalculate the smoothing parameter when sufficient new data becomes available.
5. Produce the current demand estimate.
6. Pass the estimate to the reorder calculations.

### Output / Action

- Current average daily demand estimate
- Updated forecasting parameter
- Demand input for Reorder Point Monitoring and related features

### Dependencies

Current Stock and sales or transaction records provide the historical demand information. Demand Forecasting supplies the demand estimate used by Reorder Point Monitoring and other demand-dependent features.

---

## 3. Demand Pattern & Event Adjustment

### Purpose

Demand Pattern & Event Adjustment adjusts the expected demand used for reordering when recurring seasonal patterns or known sales events are expected to change normal demand.

### Function

The feature supplements the baseline Demand Forecasting process by accounting for predictable changes caused by seasonality, promotions, campaigns, or similar planned events.

### Information Required

- Historical demand
- Baseline demand
- Item or SKU
- Location
- Calendar information
- Seasonal patterns
- Promotion or campaign schedule
- Promotion start and end dates
- Products affected by planned events
- Previous event-related demand, where available

### Process

1. Collect historical demand for the item.
2. Identify recurring demand patterns such as months, weeks, or days with consistently different demand.
3. Calculate a seasonal adjustment factor where sufficient history exists.
4. Identify upcoming promotions or planned sales events affecting the item.
5. Examine historical demand during comparable events where available.
6. Adjust the expected demand for the relevant period.
7. Pass the adjusted demand into the reordering calculations.
8. Use the baseline demand without adjustment when there is insufficient information to establish a reliable pattern.

### Output / Action

- Seasonal adjustment factor
- Event-adjusted demand estimate
- Identification of periods with increased or decreased expected demand
- Adjusted demand input for replenishment planning

### Dependencies

Demand Forecasting provides the baseline demand. The adjusted demand is used by Reorder Point Monitoring and replenishment-quantity features.

---

## 4. Demand Spike Detection

### Purpose

Demand Spike Detection identifies unusually high short-term demand that differs substantially from an item's normal demand level.

### Function

The feature identifies exceptional demand events that may require immediate attention without permanently changing the item's underlying demand forecast.

### Information Required

- Item or SKU
- Location
- Recent demand quantities
- Normal demand level
- Configured spike threshold
- Relevant demand dates
- Current inventory position
- Open replenishment orders

### Process

1. Collect recent demand transactions.
2. Obtain the item's normal demand level.
3. Compare relevant demand quantities against the configured spike threshold.
4. Identify quantities that qualify as exceptional demand.
5. Calculate the additional demand represented by the spike.
6. Recalculate projected inventory using the identified demand.
7. Signal the existing reorder process when the spike creates a replenishment condition.
8. Record the detected event for later review.

### Output / Action

- Spike or normal-demand status
- Quantity identified as exceptional demand
- Updated projected inventory
- Replenishment-review signal
- Record of detected spikes

### Dependencies

Demand Forecasting provides the normal demand level. Current Stock and incoming-order information are required to assess the effect of the spike on inventory.

---

## 5. Supplier Lead Time Monitoring

### Purpose

Supplier Lead Time Monitoring maintains information about how long suppliers take to deliver replenishment orders.

### Function

The feature compares expected supplier lead times with actual delivery times and makes updated delivery information available to the reordering process.

### Information Required

- Supplier
- Item or SKU
- Purchase or replenishment order date
- Expected delivery date
- Actual receipt date
- Stored supplier lead time
- Historical delivery records

### Process

1. Store the expected lead time for each supplier-item relationship.
2. Record the order date and expected delivery date when replenishment is created.
3. Record the actual receipt date when the order is received.
4. Calculate the actual lead time.
5. Compare actual and expected lead times.
6. Maintain recent delivery history.
7. Identify repeated differences between expected and actual delivery time.
8. Flag the relationship for review or suggest an updated lead time.

### Output / Action

- Current expected lead time
- Actual delivery-time history
- Difference between expected and actual lead time
- Delivery-delay warning
- Suggested lead-time update or review request

### Dependencies

Purchase-order and goods-receipt records provide the information required to measure supplier lead time. The resulting information is used by Safety Stock Optimization, Reorder Point Monitoring, and Supplier Selection Recommendation.

---

## 6. Supplier Reliability Scoring

### Purpose

Supplier Reliability Scoring measures how consistently a supplier meets its expected delivery commitments.

### Function

The feature provides a historical measure of supplier delivery reliability in addition to the supplier's stated lead time.

### Information Required

- Supplier
- Item or SKU
- Purchase order number
- Promised delivery date
- Actual receipt date
- On-time delivery tolerance
- Historical period
- Minimum number of completed orders required for scoring

### Process

1. Record promised and actual delivery dates.
2. Calculate delivery variance for completed orders.
3. Determine whether each order was delivered on time.
4. Calculate the percentage of orders delivered on time.
5. Calculate supporting measures such as average delivery delay and delay variability.
6. Store the resulting reliability information.
7. Display the information during supplier and reorder review.
8. Raise a supplier-performance warning when configured conditions are met.

### Output / Action

- Supplier reliability score
- On-time delivery percentage
- Average delivery delay
- Delivery-delay variability
- Supplier-performance warning

### Dependencies

Supplier Lead Time Monitoring provides delivery-history information. Supplier reliability can be displayed alongside Supplier Selection Recommendation.

---

## 7. Safety Stock Optimization

### Purpose

Safety Stock Optimization determines a recommended inventory buffer to protect against uncertainty in demand and supplier lead time.

### Function

The feature uses demand variability, lead-time variability, and a target service level to estimate the safety stock required for an item.

### Information Required

- Item or SKU
- Average demand
- Demand variability
- Average supplier lead time
- Supplier lead-time variability
- Target service level

### Process

1. Collect historical demand records.
2. Calculate demand variability.
3. Collect historical supplier lead-time records.
4. Calculate lead-time variability.
5. Estimate lead-time demand variability.
6. Apply the target service level.
7. Calculate the recommended safety stock.
8. Compare it with the current safety-stock setting.
9. Flag the item for review when the recommended value changes materially.

### Output / Action

- Recommended safety-stock quantity
- Target service level
- Demand and lead-time variability measures
- Current versus recommended safety stock
- Review flag

### Dependencies

Demand Forecasting and Supplier Lead Time Monitoring provide the demand and supplier information required by the calculation. The resulting safety stock is used by Reorder Point Monitoring.

---

## 8. Reorder Point Monitoring

### Purpose

Reorder Point Monitoring determines when an item's inventory position has reached the level at which replenishment should be considered.

### Function

The feature compares the inventory position with the calculated reorder point and identifies items requiring replenishment.

### Information Required

- Item or SKU
- Location
- Current inventory position
- Demand estimate
- Supplier lead time
- Safety stock
- Incoming supply
- Reorder point

### Process

1. Obtain the current inventory position.
2. Obtain the item's demand estimate.
3. Obtain the supplier lead time.
4. Obtain the required safety stock.
5. Calculate or retrieve the reorder point using the configured inventory policy.
6. Compare the inventory position with the reorder point.
7. Mark the item for replenishment when the reorder condition is met.
8. Pass the replenishment requirement to Replenishment Quantity Recommendation.

### Output / Action

- Reorder point
- Reorder-required status
- Replenishment trigger
- Inventory information used by downstream features

### Dependencies

Current Stock, Demand Forecasting, Supplier Lead Time Monitoring, and Safety Stock Optimization provide the principal inputs.

---

## 9. Stockout Risk Monitoring

### Purpose

Stockout Risk Monitoring identifies items that may run out of inventory before the next replenishment is expected to arrive.

### Function

The feature provides a forward-looking assessment of projected inventory and highlights potential shortages requiring attention.

### Information Required

- Item or SKU
- Current available inventory
- Expected demand
- Outstanding replenishment orders
- Expected delivery dates
- Required inventory level or reorder threshold

### Process

1. Monitor current available inventory.
2. Obtain expected demand.
3. Check outstanding replenishment and expected incoming quantities.
4. Project inventory over the relevant period.
5. Identify items whose projected inventory may become insufficient.
6. Assign a risk status.
7. Generate a replenishment-review alert.
8. Update the risk status as inventory, demand, and incoming supply change.

### Output / Action

- Stockout-risk status
- Projected inventory position
- Urgent replenishment-review alert
- Updated risk information

### Dependencies

Current Stock, Demand Forecasting, Reorder Point Monitoring, and incoming-order information provide the required data.

---

## 10. Expiry-Aware Reordering

### Purpose

Expiry-Aware Reordering adjusts replenishment decisions for products with limited shelf life.

### Function

The feature considers expiration dates of existing and incoming inventory so that stock likely to expire is not ignored during replenishment planning.

### Information Required

- Item or SKU
- Location
- Inventory quantity
- Lot or batch identifier
- Expiration date
- Expected demand and demand dates
- Incoming supply
- Expected receipt dates
- Supplier lead time
- Shelf-life requirements

### Process

1. Identify items with limited shelf life.
2. Retrieve lot or batch quantities and expiration dates.
3. Retrieve expiration dates and receipt dates for incoming supply.
4. Obtain expected demand.
5. Determine which inventory remains usable for future demand.
6. Apply a First-Expired-First-Out approach to usable stock.
7. Compare usable supply with expected demand.
8. Identify inventory approaching expiration.
9. Adjust the inventory position used for reordering.
10. Raise an expiry-risk alert where appropriate.

### Output / Action

- Usable inventory by demand period
- Expiry-risk alert
- Quantity expected to expire
- Expiry-aware reorder recommendation
- FEFO allocation sequence

### Dependencies

Current Stock, Demand Forecasting, incoming-order information, and Reorder Point Monitoring provide the information needed to incorporate shelf-life conditions.

---

## 11. Inter-Location Transfer Recommendation

### Purpose

Inter-Location Transfer Recommendation determines whether an item's replenishment requirement can be satisfied by transferring stock from another location before purchasing additional stock externally.

### Function

The feature searches approved locations for usable surplus inventory and recommends an internal transfer where suitable stock is available.

### Information Required

- Item or SKU
- Destination location
- Destination inventory position
- Available stock at other locations
- Source-location demand
- Committed quantities
- Approved transfer routes
- Transfer transport time
- Existing transfer orders

### Process

1. Identify a location requiring replenishment.
2. Search other locations for the same item.
3. Determine the stock available for transfer after considering the source location's requirements.
4. Check approved transfer routes.
5. Estimate transfer arrival time.
6. Compare available transfer quantity with the destination requirement.
7. Create a transfer recommendation when a suitable source exists.
8. Leave the requirement available for external replenishment when no suitable internal source is found.

### Output / Action

- Recommended source location
- Destination location
- Recommended transfer quantity
- Expected receipt date
- Transfer recommendation
- Reason an internal transfer could not be recommended

### Dependencies

Current Stock provides inventory information across locations. Reorder Point Monitoring identifies the destination requirement.

---

## 12. Supplier Selection Recommendation

### Purpose

Supplier Selection Recommendation identifies an appropriate approved supplier for an external replenishment requirement.

### Function

The feature evaluates eligible suppliers using purchasing information and supplier constraints before assigning a replenishment recommendation to a supplier.

### Information Required

- Item or SKU
- Required replenishment quantity
- Required receipt date
- Approved suppliers
- Supplier price
- Supplier lead time
- Minimum order quantity
- Order multiples
- Supplier availability
- Supplier-item purchasing information
- Supplier selection rule

### Process

1. Receive the replenishment requirement.
2. Retrieve approved suppliers for the item.
3. Exclude suppliers that cannot satisfy the quantity, location, timing, or ordering requirements.
4. Retrieve current supplier information.
5. Apply the configured selection rule.
6. Compare eligible suppliers.
7. Confirm that the selected supplier can meet the required delivery conditions.
8. Return the supplier recommendation and supporting information.
9. Pass the recommendation to the purchasing process.

### Output / Action

- Recommended supplier
- Expected supplier lead time
- Expected receipt date
- Supplier price
- Reason for selection
- Alternative eligible suppliers
- Alert when no eligible supplier satisfies the requirement

### Dependencies

Supplier Lead Time Monitoring and Supplier Reliability Scoring provide supplier-performance information. Replenishment Quantity Recommendation provides the replenishment requirement.

---

## 13. Replenishment Quantity Recommendation

### Purpose

Replenishment Quantity Recommendation determines the operational quantity to order after the system has identified that replenishment is required.

### Function

The feature calculates the quantity needed to restore the inventory position toward the item's configured target or maximum level while considering ordering constraints.

### Information Required

- Item or SKU
- Current inventory position
- Reorder point
- Maximum stock level
- Case size or order multiple
- Minimum order quantity, where applicable
- Replenishment lead time
- Expected delivery date
- Promised demand date, where applicable

### Process

1. Receive the replenishment requirement from Reorder Point Monitoring.
2. Determine the current inventory position.
3. Calculate the quantity required to reach the item's target maximum for a normal reorder.
4. Apply the item's case size or order multiple.
5. Handle a shortfall separately when inventory is below the required level.
6. Check timing when demand is already committed.
7. Produce the replenishment recommendation.
8. Pass the recommendation to Order Quantity Optimization and the purchasing process.

### Output / Action

- Recommended replenishment quantity
- Normal or emergency replenishment classification
- Ordering-constraint information
- Timing or shortfall alert where applicable

### Dependencies

Reorder Point Monitoring provides the reorder condition. Current Stock provides inventory position. Order Quantity Optimization may refine the quantity after this operational recommendation has been calculated.

---

## 14. Order Quantity Optimization

### Purpose

Order Quantity Optimization determines an economically appropriate replenishment quantity while respecting the operational inventory limits of the system.

### Function

The feature applies the Economic Order Quantity model to balance ordering and inventory-holding costs, then reconciles the result with the quantity that can be stored and ordered under the system's constraints.

### Information Required

- Item or SKU
- Demand estimate
- Annual demand
- Ordering cost
- Unit cost
- Annual holding-cost rate or holding cost
- Case size
- Maximum allowable quantity
- Operational replenishment quantity

### Process

1. Derive annual demand from the demand estimate.
2. Determine ordering cost and annual holding cost.
3. Calculate the EOQ using:

   `Q* = √((2 × D × S) / H)`

4. Compare neighboring whole-case quantities where case-based ordering is required.
5. Compare the cost-optimal quantity with the operational quantity and storage ceiling.
6. Select a quantity that satisfies the applicable inventory and ordering constraints.
7. Pass the optimized quantity to the purchasing process.

### Output / Action

- Cost-optimal order quantity
- Constraint-adjusted order quantity
- Quantity passed to the purchasing process

### Dependencies

Demand Forecasting supplies the demand estimate. Replenishment Quantity Recommendation provides the operational quantity and storage limits. Carrying-cost information can supply inputs used in the economic calculation.

---

## 15. Budget-Constrained Reordering

### Purpose

Budget-Constrained Reordering ensures that replenishment recommendations consider the amount of money available for inventory purchasing.

### Function

The feature adjusts or prioritizes replenishment requirements when the cost of all desired orders exceeds the available purchasing budget.

### Information Required

- Current inventory
- Demand estimate
- Reorder point or target stock
- Supplier lead time
- Unit purchase cost
- Available purchasing budget
- Minimum budget reserve, where applicable
- Stockout risk or item priority
- Outstanding purchase orders

### Process

1. Calculate normal replenishment requirements.
2. Estimate the total purchase cost.
3. Compare the required purchase cost with the available budget.
4. If the requirements fit within the budget, retain the normal recommendations.
5. If the requirements exceed the budget, prioritize the affected items using configured business rules.
6. Reduce or postpone lower-priority replenishment requirements where necessary.
7. Produce a budget-aware replenishment plan.
8. Alert the user when the available budget cannot satisfy all requirements.

### Output / Action

- Budget-aware replenishment plan
- Reorder quantities within the available budget
- Deferred or reduced replenishment requirements
- Budget-overrun warning

### Dependencies

Replenishment Quantity Recommendation provides the proposed quantities. Supplier Selection Recommendation and unit-cost information provide purchasing cost information.

---

## 16. Reorder Constraint Conflict Detection

### Purpose

Reorder Constraint Conflict Detection identifies situations where a replenishment recommendation conflicts with the rules governing inventory, suppliers, purchasing, or delivery.

### Function

The feature validates an existing replenishment recommendation instead of recalculating the reorder itself.

### Information Required

- Item or SKU
- Location
- Recommended reorder quantity
- Reorder point
- Maximum stock level
- Supplier minimum order quantity
- Order multiple or case size
- Supplier lead time
- Required receipt date
- Approved suppliers
- Available replenishment sources
- Incoming inventory
- Applicable inventory and purchasing constraints

### Process

1. Receive a replenishment recommendation.
2. Retrieve the constraints affecting the item and replenishment source.
3. Compare the proposed quantity with minimum order quantities and order multiples.
4. Check the quantity against the applicable maximum stock level.
5. Check delivery timing against the required receipt date.
6. Check whether an approved supplier or replenishment source can satisfy the requirement.
7. Identify missing or contradictory information.
8. Create a conflict record describing the affected item and conflicting rule.
9. Mark the recommendation for review.
10. Recheck the recommendation when the conflicting information changes.

### Output / Action

- Conflict status
- Affected item and recommendation
- Constraint causing the conflict
- Relevant values
- Review alert
- Updated conflict status after resolution

### Dependencies

The feature receives its proposed reorder from Replenishment Quantity Recommendation and its supplier information from Supplier Lead Time Monitoring and Supplier Selection Recommendation.

---

## 17. Purchase Order Consolidation Recommendation

### Purpose

Purchase Order Consolidation Recommendation identifies compatible replenishment requirements that can be grouped into fewer purchase orders.

### Function

The feature groups eligible purchasing requirements while preserving requirements that must remain separate because their purchasing conditions differ.

### Information Required

- Replenishment requirements
- Item or SKU
- Required quantity
- Supplier
- Supplier site, where applicable
- Purchasing organization
- Buyer
- Currency
- Unit of measure
- Required delivery date
- Ship-to location
- Existing purchase orders
- Consolidation rules

### Process

1. Collect replenishment requirements ready for purchasing.
2. Group requirements using configured purchasing attributes.
3. Check whether the requirements are compatible.
4. Identify requirements that cannot be combined.
5. Calculate combined quantities and estimated purchase values.
6. Produce a consolidation recommendation.
7. Allow the purchasing user to review the recommendation.
8. Create or update purchase orders according to the approved grouping.

### Output / Action

- Recommended purchasing groups
- Combined quantities
- Estimated purchase values
- Requirements that cannot be consolidated
- Suggested purchase-order groupings

### Dependencies

Supplier Selection Recommendation determines the supplier associated with the replenishment requirement. Reorder Constraint Conflict Detection and Budget-Constrained Reordering provide validation before the requirements enter the purchasing process.

---

## 18. ABC Inventory Classification

### Purpose

ABC Inventory Classification groups inventory items according to their relative annual consumption value so that different levels of inventory-control attention can be applied.

### Function

The feature classifies items using annual consumption value and uses the resulting classification to support reorder-review priorities.

### Information Required

- Item or SKU
- Location
- Annual usage
- Unit cost
- A-category threshold
- B-category threshold
- Classification review frequency

### Process

1. Collect annual usage and unit-cost information.
2. Calculate annual consumption value.
3. Rank items by annual consumption value.
4. Calculate percentage and cumulative percentage of total value.
5. Assign A, B, or C classifications using configured thresholds.
6. Store the classification.
7. Apply the classification to reorder-review priorities.
8. Recalculate periodically as demand and item cost change.

### Output / Action

- Annual consumption value
- Percentage and cumulative value
- ABC classification
- Reorder-review priority
- Classification-change alert

### Dependencies

Demand and cost information are required for classification. The result can be used together with Periodic Reorder Review Scheduling or other review controls, without becoming part of the core reorder calculation.

---

## 19. Inventory Turnover Monitoring

### Purpose

Inventory Turnover Monitoring measures how quickly inventory is sold or consumed and identifies items that move unusually quickly or slowly.

### Function

The feature provides inventory-movement information that can be used when reviewing reorder settings.

### Information Required

- Item or SKU
- Location
- Historical sales or consumption
- Inventory levels
- Measurement period
- Current reorder point
- Maximum stock level
- Optional target turnover

### Process

1. Collect historical sales or consumption information.
2. Determine the inventory held during the selected period.
3. Calculate the item's inventory turnover.
4. Calculate or display the estimated days inventory remains in stock.
5. Compare the result with previous periods or configured targets.
6. Flag unusually slow-moving items for review.
7. Flag unusually fast-moving items for reorder-setting review.

### Output / Action

- Inventory turnover measure
- Estimated days of inventory
- Slow-moving inventory alert
- Fast-moving inventory alert
- Reorder-setting review signal

### Dependencies

Current Stock and historical demand records provide the required inventory and consumption information.

---

## 20. Reorder Service Level Monitoring

### Purpose

Reorder Service Level Monitoring measures the fulfillment performance achieved by the reordering process and identifies items whose availability remains below the intended target.

### Function

The feature compares actual fulfillment performance with a defined service-level target and provides information for reviewing reorder policies.

### Information Required

- SKU or item identifier
- Units requested
- Units fulfilled
- Shortage or stockout events
- Target service level
- Replenishment order information
- Supplier lead-time information
- Measurement period

### Process

1. Collect demand and fulfillment records.
2. Calculate the achieved service level:

   `Service Level = (Units Fulfilled / Units Requested) × 100`

3. Count shortage events.
4. Compare achieved performance with the target.
5. Review relevant replenishment and supplier information for persistent gaps.
6. Flag the item for inventory-policy review.

### Output / Action

- Target service level
- Achieved service level
- Units requested
- Units fulfilled
- Shortage-event count
- Review alert for persistent service-level gaps

### Dependencies

Replenishment and fulfillment records provide the performance data. Supplier Lead Time Monitoring and Reorder Point Monitoring provide supporting information when investigating poor availability.

---

## Feature Integration / Dependencies

| Feature | Primary Inputs | Main Output / Consumer |
|---|---|---|
| Current Stock | Inventory records, sales, order history | Demand Forecasting, Demand Spike Detection, Reorder Point Monitoring, Stockout Risk Monitoring, Expiry-Aware Reordering, Inter-Location Transfer Recommendation, Replenishment Quantity Recommendation, Inventory Turnover Monitoring |
| Demand Forecasting | Current Stock, sales or transaction records | Demand Pattern & Event Adjustment, Demand Spike Detection, Safety Stock Optimization, Reorder Point Monitoring, Stockout Risk Monitoring, Expiry-Aware Reordering, Order Quantity Optimization |
| Demand Pattern & Event Adjustment | Demand Forecasting (baseline), calendar and event data | Reorder Point Monitoring, replenishment-quantity features |
| Demand Spike Detection | Demand Forecasting, Current Stock, incoming-order information | Reorder Point Monitoring |
| Supplier Lead Time Monitoring | Purchase-order and goods-receipt records | Supplier Reliability Scoring, Safety Stock Optimization, Reorder Point Monitoring, Supplier Selection Recommendation, Reorder Constraint Conflict Detection, Reorder Service Level Monitoring |
| Supplier Reliability Scoring | Supplier Lead Time Monitoring | Supplier Selection Recommendation |
| Safety Stock Optimization | Demand Forecasting, Supplier Lead Time Monitoring | Reorder Point Monitoring |
| Reorder Point Monitoring | Current Stock, Demand Forecasting, Supplier Lead Time Monitoring, Safety Stock Optimization | Replenishment Quantity Recommendation, Stockout Risk Monitoring, Expiry-Aware Reordering, Inter-Location Transfer Recommendation, Reorder Service Level Monitoring |
| Stockout Risk Monitoring | Current Stock, Demand Forecasting, Reorder Point Monitoring, incoming-order information | Replenishment review and prioritization |
| Expiry-Aware Reordering | Current Stock, Demand Forecasting, Reorder Point Monitoring, incoming-order information | Adjusted inventory position used in replenishment decisions |
| Inter-Location Transfer Recommendation | Current Stock, Reorder Point Monitoring | Internal replenishment decision |
| Supplier Selection Recommendation | Supplier Lead Time Monitoring, Supplier Reliability Scoring, Replenishment Quantity Recommendation | Budget-Constrained Reordering, Reorder Constraint Conflict Detection, Purchase Order Consolidation Recommendation |
| Replenishment Quantity Recommendation | Reorder Point Monitoring, Current Stock | Order Quantity Optimization, Supplier Selection Recommendation, Budget-Constrained Reordering, Reorder Constraint Conflict Detection |
| Order Quantity Optimization | Demand Forecasting, Replenishment Quantity Recommendation, carrying-cost information | Purchasing process (final replenishment quantity) |
| Budget-Constrained Reordering | Replenishment Quantity Recommendation, Supplier Selection Recommendation, unit cost | Purchase Order Consolidation Recommendation |
| Reorder Constraint Conflict Detection | Replenishment Quantity Recommendation, Supplier Lead Time Monitoring, Supplier Selection Recommendation | Purchase Order Consolidation Recommendation |
| Purchase Order Consolidation Recommendation | Supplier Selection Recommendation, Reorder Constraint Conflict Detection, Budget-Constrained Reordering | Consolidated purchase-order recommendations |
| ABC Inventory Classification | Demand and unit-cost information | Review prioritization |
| Inventory Turnover Monitoring | Current Stock, historical demand records | Reorder-setting review |
| Reorder Service Level Monitoring | Supplier Lead Time Monitoring, Reorder Point Monitoring, replenishment and fulfillment records | Reorder-policy and supplier review |
