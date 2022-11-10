# Chatty
Chat screen for Muzz application process.

### Scoping

Relatively simple exercise, intent is to show I can create a simple vertical slice including data and UI layer using modern libraries and architecture. Brief details five feature requirements: three on the UI side (Message List, Text Entry Box, User Header), one on the data side (observable persistence layer), and one on functional side (switch users).

I will implement the UI using Compose. **Message List** should be a simple `LazyColumn` with conditional logic for whether to format as message or reply, show tail, and section. **Text Entry Box** will be an `OutlinedTextField`, likely a custom one to reduce internal padding. **User Header** can likely be a simple `TopAppBar` with a couple of icons.

For the data layer, Room seems like the easiest choice. Simple table of messages will suffice (adding something for users or conversations, is an overkill), with a single Dao and Repository on top. I will use a `CoroutineWorker` to prepopulate initial messages, as it works nicely with Room and is what I would use in production.

For features, sending messages should add them to the database. Switching users could be done using top-right ellipsis button (for the demo, probably just hooking a basic flag like `isUserSarah` will be enough). For completeness, clicking the top-left arrow should reset the database to initial state.

Below are some of the constraints - things I would probably do if this was a real app and I had complete design specs:
- More structured Material theming: dynamic color, better typography, animations etc.
- Better data layer: store conversations for multiple users
- Networking: get messages from a server, offline-first architecture with local persistence

Here are some stretch goals - things I will consider adding once I've finished the demo:
- Modularize: enforces code encapsulation, improves build experience for larger projects, enhances testability
- Test: normally I'd write tests as I develop each piece of the code, but for the sake of simplicity I'll initially skip that
- Pixel-perfect: consider trying to emulate the UI more closely after setting up screenshot tests - difficult without proper design specs so might skip

### Assumptions

Scoping outlined some of the high-level assumptions. Here are any implementation-specific considerations that I encounter as I work on the demo:
- **Compose Component Reusability**: As I wrote the components to be used in the `MainScreen` composable, I took basic steps to potentially make them more reusable. As there are no other screens, this is quite rudimentary -- having a better idea of the larger design picture, I would probably model these components differently. For the prototype, this approach was okay, if a little overengineered.
