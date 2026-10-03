# Analytics and Security Integration

## Feature 1: Smart Stock Trends Dashboard

### Description
A dashboard that turns inventory records into clear charts and summaries showing best-selling products, slow-moving items, and products that are about to run out.

### How It Works
The system reads stock addition, sale, return, and removal records. It calculates sales totals over chosen periods (such as 7 days, 30 days, or 3 months) and estimates how many days remaining stock will last. Results appear as a bar chart of top sellers, a list of slow-moving products, and a “Running Low” panel. Users can filter by category, date range, or warehouse.

---

## Feature 2: Slow-Moving and Dead Stock Analytics

### Description
This feature identifies products that sell slowly or not at all, so managers can free warehouse space and recover capital tied up in non-performing stock.

### How It Works
The system reviews sales history for every product, checking the last sale date and sales frequency. Products are grouped as Active (sold within 30 days), Slow-Moving (30–90 days), or Dead (over 90 days). The dashboard shows quantity on hand, monetary value locked, and days since last sale. Managers can filter by category, warehouse, or supplier and receive monthly summary reports.

---

## Feature 3: Predictive Stock Reorder Analytics

### Description
This feature forecasts when items will run out and suggests optimal reorder quantities using historical sales, seasonal patterns, and supplier lead times.

### How It Works
The system tracks inventory movement, sales rates, and supplier delivery history. It calculates consumption speed and highlights items approaching critical levels before a stockout occurs. When a threshold is near, the system displays a recommended reorder quantity and prepares a draft purchase order for manager review.

---

## Feature 4: Inventory Expiry Risk Prediction

### Description
This feature identifies products likely to expire before they are sold, helping reduce waste and financial loss.

### How It Works
The system monitors product expiry dates, current stock quantities, and past sales records. It compares remaining shelf life with sales rate. When a product is at risk of expiring before sale, an alert appears on the dashboard with suggested actions such as prioritizing sale, reducing price, or limiting future purchases.

---

## Feature 5: Low-Stock Analytics Dashboard

### Description
A focused dashboard that highlights products whose quantities have reached or fallen below the minimum stock level and shows how many units need restocking.

### How It Works
The system regularly compares current quantity of each product with its predefined minimum level. Items at or below the minimum appear on the dashboard with product name, current quantity, minimum level, stock status, and units required to restock. This helps managers act before products run out.

---

## Feature 6: Low-Stock Trend Analysis

### Description
This feature tracks products that repeatedly fall below minimum stock levels so the business can plan more frequent restocking for high-demand items.

### How It Works
The system examines stock levels over time and identifies products that regularly drop below the minimum threshold. These items are listed in a report so inventory managers can adjust reorder frequency and avoid recurring shortages.

---

## Feature 7: Stock Level Analytics

### Description
A simple status overview that classifies every product as In Stock, Low Stock, or Out of Stock based on current quantity.

### How It Works
The system records stock coming in and going out. It compares available quantity against set thresholds and displays the status for each product. Managers can quickly see which items need restocking and reduce the risk of running out of important products.

---

## Feature 8: Sales and Inventory Trend Analysis

### Description
This feature shows how product demand changes over time by comparing sales and inventory records across different periods.

### How It Works
The system collects completed sales and inventory data. It compares units sold on a daily, weekly, or monthly basis and presents the results in charts or reports. Businesses can see which products have increasing or decreasing demand and adjust purchasing accordingly.

---

## Feature 9: Stock Adjustment Pattern Analysis

### Description
This feature analyzes manual changes made to inventory quantities to detect unusual or repeated adjustments that may indicate mistakes or unauthorized activity.

### How It Works
Every manual stock adjustment is recorded with user, product, quantity changed, and time. The system reviews the history for suspicious patterns (for example, repeated reductions of the same product). Flagged patterns are shown to an authorized manager for investigation.

---

## Feature 10: Inventory Analytics Dashboard

### Description
A general dashboard that presents key inventory insights such as current stock levels, fast-moving products, slow-moving products, and low-stock items.

### How It Works
Whenever products are added, sold, or removed, the system collects the data, analyzes it, and displays charts, tables, and summaries. Managers can see at a glance which products are running low and which are selling fastest, supporting better restocking decisions.

---

## Feature 11: Role-Based Access Control

### Description
This security feature ensures each user can only see and perform actions allowed by their assigned role, protecting sensitive inventory data from unauthorized changes.

### How It Works
Users are assigned roles such as Administrator, Manager, Staff, or Viewer. When a user logs in, the system checks the role and shows only the menus and functions permitted for that role. Even if someone tries to reach a restricted page directly, the system blocks the action and displays an access-denied message.

---

## Feature 12: Activity Logging and Audit Trail

### Description
A complete record of important actions performed in the system so administrators can see who did what and when.

### How It Works
Each significant action (login, quantity change, product deletion, price change, failed login) is saved with the username, date, time, and a short description. Administrators can search the log by user, date, or action type to investigate unexpected stock changes or other problems.

---

## Feature 13: Sensitive Action Audit Trail with Anomaly Flagging

### Description
This feature logs high-risk actions and automatically flags unusual patterns for administrator review, improving accountability and deterring misuse.

### How It Works
Sensitive actions such as manual quantity changes, product deletions, price changes, and permission updates are logged with user name, role, action details, date/time, and IP address. Logs are stored in a read-only area. Simple rules detect anomalies (many adjustments in a short time, actions outside work hours, or staff attempting high-level actions). Flagged entries are highlighted and an alert is sent to the administrator.

---

## Feature 14: Unusual Inventory Access Detection

### Description
This feature spots user activities that differ from normal patterns, helping prevent unauthorized stock changes and account misuse.

### How It Works
The system records stock adjustments, deletions, quantity changes, and login attempts. It compares each user’s current activity with their usual behavior and assigned permissions. When unusual activity is detected (for example, suddenly changing large numbers of records), a security alert is generated for the administrator to review.

---

## Feature 15: Inventory Access Heatmap

### Description
A visual display showing when and how often users access different parts of the inventory system, making unusual activity easy to spot.

### How It Works
The system records access to inventory functions and organizes the data by user, system section, and time period. Activity is shown as a heatmap. Areas with unusually high or unexpected access stand out so the administrator can examine the related records.

---

## Feature 16: Login Security and Failed Login Alerts

### Description
This feature protects the system from unauthorized access by verifying credentials and alerting administrators about repeated failed login attempts.

### How It Works
Users log in with username or email and password. The system verifies the details before granting access. Failed attempts are recorded. When several unsuccessful attempts occur, an alert is sent to the administrator so possible unauthorized access can be investigated.

---

## Feature 17: Automatic Logout on Inactivity

### Description
This security feature automatically signs a user out after a period of inactivity, protecting inventory data when a workstation is left unattended.

### How It Works
The system monitors how long a user has been inactive. If no activity occurs for a set period, the user is logged out and must sign in again to continue. This reduces the risk of unauthorized use of an open session.

---

## Feature 18: Secure User Access and Activity Monitoring

### Description
A combined security feature that limits what each user can do according to their role and keeps a record of important inventory actions.

### How It Works
Each staff member logs in with a username and password and receives access rights based on their role (for example, a manager can add, edit, and delete products while staff can only view and update stock). The system also records who added, edited, or deleted products, making unauthorized changes easier to detect.

---

## Feature 19: Low Stock Alert and Report

### Description
This feature notifies the store owner when products reach their minimum stock level and includes those products in a low-stock report.

### How It Works
The system continuously monitors product quantities. When a product reaches its minimum level, an alert is sent to the owner and the product is added to a low-stock report so restocking can be planned promptly.

---

## Feature 20: Stock Reorder Analysis

### Description
An analytics feature that identifies products whose quantity has fallen below the set minimum stock level so new stock can be ordered in time.

### How It Works
The system monitors the quantity of each product. When quantity falls below the defined minimum, the product is flagged as needing a reorder and the information is displayed to the administrator so stock can be purchased before it runs out.
