# BusyInv [![Circle CI](https://circleci.com/gh/Cnly/BusyInv.svg?style=svg)](https://circleci.com/gh/Cnly/BusyInv)

An Inventory GUI lib for Bukkit. `Still in development. API may change frequently.`

This can be a standalone plugin which does nothing but lets other plugins to hook in. But if you like, you can pack it into your plugin jar too.

If you choose to pack it into your jar, a relocation for this lib is strongly recommended to avoid conflict between multiple versions of BusyInv. To pack it into your jar and do the relocation, you can add the following to your pom.xml if you use Maven:
```xml
			<dependency>
				<groupId>io.github.Cnly.BusyInv</groupId>
				<artifactId>BusyInv</artifactId>
				<version>${busyinv.version}</version>
				<scope>compile</scope>
			</dependency>

			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-shade-plugin</artifactId>
				<version>2.3</version>
				<executions>
					<execution>
						<phase>package</phase>
						<goals>
							<goal>shade</goal>
						</goals>
						<configuration>
							<minimizeJar>true</minimizeJar>
							<createDependencyReducedPom>false</createDependencyReducedPom>
							<relocations>
								<relocation>
									<pattern>io.github.Cnly.BusyInv</pattern>
									<shadedPattern>${project.groupId}.BusyInv</shadedPattern>
								</relocation>
							</relocations>
						</configuration>
					</execution>
				</executions>
			</plugin>
```

And don't forget to call `BusyInv.registerFor(JavaPlugin);` in `onEnable()` or somewhere else if you are going to pack it!

Some ideas in this project were from [AmpMenus](https://github.com/ampayne2/AmpMenus).
