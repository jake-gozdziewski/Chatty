# Chatty
Chat screen for Muzz application process. See the Demo Recording.webm for a quick overview.

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
- **Database**: To avoid overthinking about how to model the database schema, a basic one was created for the purpose of showing the chat messages while having the potential to be extended to multiple users/conversations.
- **Logic**: code with a lot of logic like conversions between `Message` and `ChatMessage` would normally be isolated for easier testing. In the case of `ChatViewModel` this would reduce readability, so I decided to keep things simpler if less testable.
- **User Switching**: the current implementation is very rudimentary to keep things simple, and would have to be redone if this was a production feature.

### Design 

With the core demo finished, below is a brief documentation on its design.

The app roughly follows MVVM architecture with two layers: Data and UI. Data layer consists of a Room database (`MessageDatabase`) to persist user messages, DAO and repository for interfacing with the data, as well as a `DatabaseWorker` which prepopulates the database. The UI layer is comprised of Compose components which consistute the chat screen (`MainScreen`), as well as of app theming and `ChatViewModel`, which interacts with `MessageRepository` and provides UI state for `MainScreen`.

For libraries, we primarily use Compose for UI, Room for database, Hilt for dependency injection and Work Manager for prepopulating the database. Accompanist's [System UI Controller](https://github.com/google/accompanist/tree/main/systemuicontroller) is used to set the color of system UI status bars, something that is otherwise very tricky to do without using XML theming. 

Implementation-wise, I was somewhere between trying to think like I'm building for production and keeping everything as simple as possible. The only exception to this is testing, which I would normally do immediately after developing each code segment. I wanted to make the demo relatively quickly and initial infra set up for robust tests on Android takes time, so I decided to skip them entirely.

The project feels complete as it is, so I will skip the stretch goals and instead talk about what I would do with them if I had the time:
- **Modularize**: Split the app into four modules: app module, chat feature, message database, core ui (theming). This would force code encapsulation, make testing easier and (though slightly at this scale) improve build times by allowing parallel task execution. Although a bit of an overkill here, I would use convention plugins in the style of [Now in Android](https://github.com/android/nowinandroid/blob/main/docs/ModularizationLearningJourney.md) to greatly simplify individual module Gradle scripts
- **Test**: Test each Compose component for functionality, with Screenshot testing using [Paparazzi](https://github.com/cashapp/paparazzi) to protect against visual regressions. Unit test `ChatViewModel` and the data layer (substituting fake repository and in-memory database using Hilt where necessary). Benchmark startup performance and scrolling jank using Macrobenchmark library, generating a Baseline profile to improve these metrics.
- **Pixel-perfect**: Use Paparazzi screenshot tests to get a better idea of how far off I am from screenshots, and fine-tune layout, colors and gradients to get as close as possible. The only difference would be an avatar, which I couldn't easily replicate 1:1.
