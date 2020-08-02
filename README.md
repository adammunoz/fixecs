# Fix ECS Task Definition

A CLI tool designed to re-order **Terraform ECS Task Definition template files** in order to match the order registered on AWS.
Solves this issue: https://github.com/terraform-providers/terraform-provider-aws/issues/3035

**Note:**
iI seems the issue may has already been fixed in recent versions of the AWS provider (although I have not been able to confirm it yet).
In that case, this tool can be helpful for older versions.

## Usage

There are 2 ways to run this tool. In both cases, Java must be installed in the system.

### Leiningen

Clone this repository and then run:

`lein run <path-to-task.json> <task-name>`

If you don't have Leiningen, it can be installed here https://leiningen.org/ .

### Java JAR

Download JAR file from https://github.com/adammunoz/fixecs/releases/ .

Then run:

`java -jar fixecs-0.1.0-stanadlone.jar <path-to-task.json> <task-name>`

(Replace 0.1.0 with the verion of the actual downloaded version).

## License

Copyright © 2020 Adam Munoz

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
