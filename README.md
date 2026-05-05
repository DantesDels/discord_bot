# 🤖 Boris le Bot - Discord Bot en Java

Boris est un bot Discord interactif développé dans le cadre d'un TP de Java avancé. Le projet implémente une architecture robuste (celle proposée par le TP) basée sur le **Command Pattern** et utilise les dernières fonctionnalités de **JDA 6** et **Java 21** avec **Gradle 9.5.0** (si vous utilisez autre chose, force à vous : JDA n'est pas compatible avec les autres version de JDK, donc JDK21 oblige).

---

## 🛠️ Stack Technique
* **Langage** : Java 21
* **Librairie** : JDA 6.4.1 (Java Discord API)
* **Build Tool** : Gradle 9.5.0
* **Gestionnaire de dépendances** : Maven Central
* **Sécurité** : Dotenv-java pour la gestion des variables d'environnement

---

## 🚀 Fonctionnalités
Le bot a été migré vers le système de **Slash Commands** pour une intégration native et fluide dans Discord :

* `/ping` : Vérifie la réactivité du bot (répond "Pong !").
* `/help` : Affiche dynamiquement la liste des commandes et leurs descriptions via un **Embed**.
* `/joke` : Récupère une blague aléatoire depuis une API REST externe de manière asynchrone.
* `/poll` : Génère un sondage avec une question et deux options, incluant l'ajout automatique de réactions pour le vote.

---

## 📁 Architecture du Projet
Le projet respecte une structure modulaire stricte pour faciliter la maintenance et l'évolutivité :

* **Main.java** : Point d'entrée, initialise le bot, charge le Token et enregistre les commandes auprès de Discord.
* **CommandManager.java** : Gère la Map des commandes, le dispatching des événements Slash et le logging.
* **ICommand.java** : Interface définissant le contrat pour toutes les commandes du bot.
* **CommandListener.java** : Écoute les interactions Discord et les transmet au manager.

---

## ⚙️ Installation et Lancement

### 1. Prérequis
* Avoir le **JDK 21** installé **OBLIGATOIREMENT** (avec Gradle 9.5.0)
* Créer une application sur le [Discord Developer Portal](https://discord.com/developers/applications).
* Activer le **Message Content Intent** dans l'onglet "Bot" du portail.

### 2. Configuration
Créez un fichier `.env` à la racine du projet pour stocker votre Token en toute sécurité :
```env
TOKEN=votre_token_secret_ici
```