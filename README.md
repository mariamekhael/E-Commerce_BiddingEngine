# 🛒 E-Commerce & Bidding Engine

> A Java console application that brings the thrill of live auctions and everyday shopping into one clean, role-driven marketplace — built from the ground up on solid OOP principles.

---

## What Is This?

Ever wondered how platforms like eBay work under the hood? This project is a hands-on simulation of exactly that.

Three types of users walk into a marketplace — an **Admin**, a **Seller**, and a **Buyer** — and each one has a completely different experience. Sellers list products and kick off timed auctions. Buyers browse, bid against each other in real time, or just buy outright if no one's placed a bid yet. And the Admin? They hold the hammer — literally closing auctions and settling the final price.

It's not just a CRUD app. There's real auction logic here: countdowns, bid validation, wallet checks, and live notifications to anyone who's been outbid.

---

## What Can It Do?

### For Buyers
- Browse all currently listed products
- **Buy directly** at the base price — but only if no one's bid yet (fair's fair)
- **Place a bid** — the system checks your wallet, validates your amount is higher than the current top bid, and blocks you if the auction timer has run out
- Get **instantly notified** whenever someone outbids you on a product you've bid on

### For Sellers
- List a new product with a title, description, and base price
- Set an **auction duration** in minutes — the clock starts ticking immediately

### For Admins
- **Close any auction** manually — the system automatically handles the money transfer, updates both wallets, and drops the product into the winner's cart

---

## Design Decisions Worth Knowing

### Observer Pattern — Real-Time Outbid Alerts
When a buyer places a bid, every *previous* bidder on that product gets a notification. This is implemented through a custom `Observer` interface that `Buyer` implements. The active bidder is excluded from the alert — no point telling someone they just outbid themselves.

```
Buyer A bids 500  →  [subscribed to product]
Buyer B bids 700  →  Buyer A gets notified: "New bid on (iPhone 15) → 700"
Buyer C bids 900  →  Buyer A & B both get notified
```

### Inheritance & Polymorphism
`Admin`, `Seller`, and `Buyer` all extend an abstract `User` class. The `EngineController` uses `instanceof` checks to route each user to the right dashboard after login — clean and extensible.

### Encapsulation
Wallet balances, product lists, and bid histories are all kept private. Everything goes through proper getters and setters, with basic guards (e.g., wallet balance can't go negative).

---

## Project Structure

```
ECommerceBiddingEngine/
│
├── ECommerceBiddingEngine.java   ← Entry point, just kicks off the controller
├── EngineController.java         ← The brain — handles menus, routing, and all user flows
│
├── User.java                     ← Abstract base class (id, name, username, password)
├── Admin.java                    ← Can close auctions
├── Seller.java                   ← Lists products, earns money from sales
├── Buyer.java                    ← Bids, buys, gets notified (implements Observer)
│
├── Product.java                  ← Holds product info + manages observer subscriptions
├── AuctionListing.java           ← The auction wrapper: timer, bids, highest bidder, settlement
├── Bid.java                      ← A single timestamped bid record
│
└── Observer.java                 ← The interface that makes notifications work
```

---

## Getting Started

### Prerequisites
- Java 8 or higher
- Any IDE (IntelliJ, Eclipse, NetBeans) or just a terminal

### Run It

```bash
# Compile all files
javac *.java

# Run the application
java ECommerceBiddingEngine
```

That's it. The console menu will guide you from there.

---

## Pre-Loaded Test Accounts

The app seeds three accounts on startup so you can jump straight in:

| Role   | Username | Password | Starting Balance |
|--------|----------|----------|-----------------|
| Admin  | admin    | 123      | —               |
| Seller | seller   | 123      | $0              |
| Buyer  | buyer    | 123      | $5,000          |

You can also register new Buyer or Seller accounts from the main menu.

---

## A Typical Flow

1. **Login as Seller** → Add a product (e.g., "iPhone 15", $800 base, 5-minute auction)
2. **Login as Buyer** → Place a bid of $850
3. **Register a second Buyer** → Outbid with $1,000 → first buyer gets a notification
4. **Login as Admin** → Close the auction → $1,000 moves from buyer's wallet to seller's

---

## OOP Concepts Covered

| Concept | Where It Shows Up |
|---|---|
| **Abstraction** | `User` is abstract — you can't instantiate it directly |
| **Inheritance** | `Admin`, `Seller`, `Buyer` all extend `User` |
| **Polymorphism** | `redirectUserMenu()` handles any `User` subtype |
| **Encapsulation** | Private fields with controlled access throughout |
| **Interface** | `Observer` interface implemented by `Buyer` |
| **Design Pattern** | Observer pattern for real-time bid notifications |

---

## Notes & Limitations

- Data is **in-memory only** — everything resets when you close the app
- Passwords are stored in plain text (this is a learning project, not production!)
- The auction timer is based on the system clock — if you set a 1-minute auction, it genuinely expires in 60 seconds

---

*Built as a Java OOP learning project. Feel free to fork, extend, or break it.*
