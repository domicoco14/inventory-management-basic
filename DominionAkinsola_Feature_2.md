# Stock Audit Feature 2: Audit-to-Reorder Feedback Loop

**Name:** Dominion Akinsola
**Matric No:** [YOUR MATRIC NUMBER]
**GitHub Username:** [YOUR GITHUB USERNAME]

---

## 1. Overview

The Audit-to-Reorder Feedback Loop connects stock audit results directly to purchasing decisions. After each audit, the system uses the findings (shortages, damage, expiry losses, and repeated variances) to automatically adjust reorder levels, safety stock, and control flags for each item.

In most businesses, an audit ends with a report that someone reads and files. This feature makes the audit useful: the findings change how the system behaves, so the same problems become less likely to repeat.

---

## 2. The Problem It Solves

- **Audit findings are ignored.** Reports are produced but rarely change how stock is ordered or controlled.
- **Hidden losses affect ordering.** If an item regularly loses stock to theft, damage, or miscounts, the recorded quantity is higher than reality. The system then reorders too late and causes stockouts.
- **Fixed reorder levels.** Reorder levels are often set once and never reviewed, even when real conditions change.
- **Repeated shrinkage.** Items that keep going missing receive no extra attention because nobody connects the audits together.

---

## 3. How It Works (Step by Step)

1. **Audit is completed.** Counts are compared with system records and variances are recorded per item.
2. **Variance analysis.** The system classifies each variance by cause: theft or unexplained loss, damage, expiry, counting error, or supplier shortage.
3. **Pattern detection.** The system looks at the history of audits for each item. One small variance is noted. A repeated variance across several audits is treated as a pattern.
4. **Automatic adjustment.** Based on the pattern, the system updates:
   - **Safety stock:** raised for items that regularly lose stock, so a real stockout is avoided.
   - **Reorder level:** adjusted to reflect the true usable quantity, not the inflated recorded one.
   - **Order quantity:** changed for items that expire or become obsolete before they sell.
5. **Control flags.** Items with repeated shrinkage are flagged for tighter control, such as more frequent cycle counts, restricted access, or manager approval for adjustments.
6. **Manager approval.** Changes that are large or high in value are sent to a manager for approval before they are applied.
7. **Feedback is tracked.** After the change, the next audit shows whether the variance improved, so the system learns whether the adjustment worked.
8. **Loop repeats.** Every audit feeds the next round of adjustments, which keeps reorder settings accurate over time.

---

## 4. Key Components

| Component | Purpose |
|---|---|
| Variance classifier | Sorts differences by cause (theft, damage, expiry, error) |
| Pattern engine | Detects repeated issues across several audits |
| Reorder rules engine | Updates reorder level, safety stock, and order quantity |
| Control flagging | Marks high-risk items for extra checks |
| Approval workflow | Lets managers review major changes |
| Results tracking | Measures whether the change improved accuracy |

---

## 5. Example

A shop sells bags of rice. Its reorder level is 10 bags and safety stock is 5 bags.

- In three monthly audits, the system records 4, 5, and 4 bags missing.
- The pattern engine sees consistent shrinkage and classifies it as unexplained loss.
- The system raises safety stock from 5 to 10 bags and raises the reorder level from 10 to 15 bags.
- The item is flagged for weekly cycle counts.
- The manager approves the change.
- In the next audit, the loss falls to 1 bag, so the system keeps the new settings and records that the control worked.

Without this feature, the shop would keep believing it had enough rice and would run out unexpectedly.

---

## 6. Benefits

- **Audits lead to action,** instead of becoming forgotten reports.
- **Fewer stockouts,** because reorder levels reflect real conditions.
- **Less overstocking,** because order quantities match what actually sells.
- **Reduced losses,** since repeated shrinkage triggers tighter controls.
- **Better purchasing decisions,** based on real audit evidence.
- **Continuous improvement,** because every audit improves the next one.

---

## 7. Relevance to an Inventory Management System

An IMS depends on accurate data to decide when and how much to reorder. Audits find where the data is wrong, but without a link to purchasing, the correction stays in the report. This feature closes that gap.

It supports core IMS goals:

- **Replenishment accuracy:** reorder points and safety stock stay realistic.
- **Loss prevention:** problem items receive extra control automatically.
- **Working capital:** money is not tied up in stock that expires or never sells.
- **Management insight:** managers see which items, causes, and locations cause the most problems.

---

## 8. Possible Challenges and Solutions

| Challenge | Solution |
|---|---|
| One-off variance causes a wrong adjustment | Require a repeated pattern before changing settings |
| Safety stock grows too high | Set maximum limits and review them regularly |
| Automatic changes may be wrong | Require manager approval for large or high-value changes |
| Not enough audit history | Use manual settings until enough data is collected |

---

## 9. Conclusion

The Audit-to-Reorder Feedback Loop turns stock audits into a tool for improvement. By feeding audit findings into reorder levels, safety stock, and control flags, it keeps inventory records realistic, reduces losses, and improves purchasing decisions. For an inventory management system, it means the audit does not just find problems, it helps prevent them.
